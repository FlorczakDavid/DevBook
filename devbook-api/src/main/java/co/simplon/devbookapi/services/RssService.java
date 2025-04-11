package co.simplon.devbookapi.services;

import co.simplon.devbookapi.dtos.RssProviderCreate;
import co.simplon.devbookapi.dtos.RssUrlCreate;
import co.simplon.devbookapi.entities.Article;
import co.simplon.devbookapi.entities.RssProvider;
import co.simplon.devbookapi.repositories.ArticleRepository;
import co.simplon.devbookapi.repositories.RssProviderRepository;
import com.apptasticsoftware.rssreader.Channel;
import com.apptasticsoftware.rssreader.Image;
import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.*;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class RssService {
    private static final Logger logger = LoggerFactory.getLogger(RssService.class);

    private final RssProviderRepository providerRepository;
    private final ArticleRepository articleRepository;
    private final Validator validator;

    public RssService(RssProviderRepository providerRepository, ArticleRepository articleRepository) {
        this.providerRepository = providerRepository;
        this.articleRepository = articleRepository;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


    public RssProvider fetchAndStoreFrom(RssUrlCreate input) throws IOException {
        RssReader rssReader = new RssReader();
        rssReader.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36");
        RssProvider provider = null;
        List<Item> items = rssReader.read(input.url()).toList();

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
        String feedUrl = input.url();
        String description = channel.getDescription();
        Optional<Image> imageOptional = channel.getImage();
        String imageUrl = imageOptional.map(Image::getUrl).orElse(null);
        ZonedDateTime lastUpdate = channel.getLastBuildDateZonedDateTime().orElse(null);

        RssProviderCreate providerCreate = new RssProviderCreate(feedUrl, feedLink, feedTitle, description, imageUrl, lastUpdate);
        validateRssProviderCreate(providerCreate);

        // Vérifier si le fournisseur existe déjà ou le créer
        provider = providerRepository.findByLink(feedLink)
                .orElseGet(() -> createProviderFromDto(providerCreate));


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
        return provider;
    }

    private RssProvider createProviderFromDto(RssProviderCreate providerCreate) {
        RssProvider provider = new RssProvider();
        provider.setUrl(providerCreate.url());
        provider.setLink(providerCreate.link());
        provider.setTitle(providerCreate.title());
        provider.setDescription(providerCreate.description());
        provider.setLastUpdate(providerCreate.lastUpdate());
        return providerRepository.save(provider);
    }

    private Article createArticle(Item itemData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        Article article = new Article();
        article.setUrl(itemData.getLink().orElse(null));
        article.setTitle(itemData.getTitle().orElse(null));
        article.setPublishedDate(itemData.getPubDateZonedDateTime().orElse(null));
        article.setAuthors(extractAuthor(itemData.getAuthor().orElse(""), objectMapper));
        article.setCategories(new HashSet<>(itemData.getCategories()));

        String description = itemData.getDescription().orElse("");
        String cleanedDescription = Jsoup.clean(description, Safelist.basic());
        article.setDescription(
                cleanedDescription.length() > 255 ? cleanedDescription.substring(0, 255) : cleanedDescription
        );

        // Log des données de l'article
        logger.info("Article à sauvegarder : {}", article);
        validateArticle(article);
        return article;
    }

    private String extractAuthor(String name, ObjectMapper objectMapper) throws IOException {

        // Vérifier si le contenu est un tableau JSON
        if (name.startsWith("[") && name.endsWith("]")) {
            // Désérialiser le tableau JSON
            List<String> authors = objectMapper.readValue(name, new TypeReference<List<String>>() {
            });
            // Retourner les auteurs sous forme de chaîne
            return String.join(", ", authors);
        }
        // Sinon, retourner directement le contenu
        return name;
    }

    public List<Article> findArticlesByCategory(String category) {
        return articleRepository.findAllByCategoriesContaining(category);
    }

    private void validateArticle(Article article) {
        Set<ConstraintViolation<Article>> violations = validator.validate(article);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation échouée : ");
            for (ConstraintViolation<Article> violation : violations) {
                errorMessage.append(violation.getPropertyPath()).append(" ").append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }


    private void validateRssProviderCreate(RssProviderCreate providerCreate) {
        Set<ConstraintViolation<RssProviderCreate>> violations = validator.validate(providerCreate);
        if (!violations.isEmpty()) {
            logger.info("Validation échouée pour le fournisseur RSS : {}", providerCreate);
            throw new ValidationException("Validation échouée pour RssProviderCreate", (Throwable) violations);
        }
        logger.info("Validation réussie pour le fournisseur RSS : {}", providerCreate);
    }
}
