package co.simplon.devbookapi.services;

import co.simplon.devbookapi.dtos.ArticleCreate;
import co.simplon.devbookapi.dtos.ArticleView;
import co.simplon.devbookapi.entities.Article;
import co.simplon.devbookapi.repositories.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleView postArticle(ArticleCreate input) throws IOException {
        try{
            Article article = createArticle(input);
            return new ArticleView(
                    article.getUrl(),
                    article.getTitle(),
                    article.getImagePath(),
                    article.getDescription(),
                    article.getAuthors()
            );
        }catch(IOException e){
            System.out.println("Cannot access to the article");
        }
        return null;
    }

    private Article createArticle(ArticleCreate input) throws IOException {
        Document doc = Jsoup.connect(input.url()).get(); //https://www.mediapart.fr/
        Elements title = doc.select("meta[property='og:title']");
        String contentTitle = title.attr("content");
        Elements type = doc.select("meta[property='og:type']");
        String contentType = type.attr("content");
        Elements image = doc.select("meta[property='og:image']");
        String contentImage = image.attr("content");
        Elements url = doc.select("meta[property='og:url']");
        String contentUrl = url.attr("content");
        Elements description = doc.select("meta[property='og:description']");
        String contentDescription = description.attr("content");
        Elements author = doc.select(String.format("meta[property='%s1:author']", contentType));
        String contentAuthor = author.attr("content");

        Article article = new Article();
        article.setUrl(contentUrl);
        article.setTitle(contentTitle);
        article.setImagePath(contentImage);
        article.setDescription(contentDescription);
        article.setAuthors(contentAuthor);
        return articleRepository.save(article);
    }
}
