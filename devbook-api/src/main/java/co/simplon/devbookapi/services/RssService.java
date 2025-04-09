package co.simplon.devbookapi.services;

import co.simplon.devbookapi.entities.Article;
import co.simplon.devbookapi.entities.RssProvider;
import co.simplon.devbookapi.repositories.ArticleRepository;
import co.simplon.devbookapi.repositories.RssProviderRepository;
import com.apptasticsoftware.rssreader.Channel;
import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class RssService {
    private final RssProviderRepository providerRepository;
    private final ArticleRepository articleRepository;

    public RssService(RssProviderRepository providerRepository, ArticleRepository articleRepository) {
        this.providerRepository = providerRepository;
        this.articleRepository = articleRepository;
    }


    public RssProvider fetchAndStoreFrom(String url) throws IOException {
        RssReader rssReader = new RssReader();
        List<Item> items = rssReader.read(url).toList();

        if (items.isEmpty()) {
            throw new IllegalArgumentException("Aucun élément trouvé dans le flux.");
        }

        // Récupérer les métadonnées du Channel à partir du premier Item
        Channel channel = items.getFirst().getChannel();
        if (channel == null) {
            throw new IllegalArgumentException("Impossible de récupérer les métadonnées du Channel.");
        }

        String feedTitle = channel.getTitle();
        String feedLink = channel.getLink();
        String feedSubtitle = channel.getDescription();

        ZonedDateTime lastUpdate = channel.getLastBuildDateZonedDateTime().orElse(null);

        // Vérifier si le fournisseur existe déjà ou le créer
        RssProvider provider = providerRepository.findByLink(feedLink)
                .orElseGet(() -> createProvider(feedTitle, feedLink, feedSubtitle, lastUpdate));

        // Traiter les Items
        for (Item item : items) {
            String link = item.getLink().orElse(null);
            if (link == null || articleRepository.existsByUrl(link)) {
                continue;
            }
            Article article = createArticle(item);
            article.setProvider(provider);
            articleRepository.save(article);
        }

        // Ajouter les articles au fournisseur
        Set<Article> sortedItems = new TreeSet<>(Comparator.comparing(Article::getId));
        sortedItems.addAll(articleRepository.findByProviderOrderByIdAsc(provider));
        return provider;
    }

    private RssProvider createProvider(String title, String link, String subtitle, ZonedDateTime updated) {
        RssProvider provider = new RssProvider();
        provider.setTitle(title);
        provider.setLink(link);
        provider.setSubtitle(subtitle);
        provider.setLastUpdate(updated);
        providerRepository.save(provider);
        return provider;
    }

    private Article createArticle(Item itemData) throws IOException {
        Article article = new Article();
        article.setUrl(itemData.getLink().orElse(null));
        article.setTitle(itemData.getTitle().orElse(null));
        article.setDescription(itemData.getDescription().orElse(null));
        article.setPublishedDate(itemData.getPubDateZonedDateTime().orElse(null));
        article.setAuthors(itemData.getAuthor().orElse(null));
        article.setCategories(new HashSet<>((List<String>) itemData.getCategories()));
        return article;
    }
}
