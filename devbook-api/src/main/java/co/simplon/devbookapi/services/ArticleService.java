package co.simplon.devbookapi.services;

import co.simplon.devbookapi.dtos.ArticleCreate;
import co.simplon.devbookapi.dtos.ValidArticle;
import co.simplon.devbookapi.entities.Article;
import co.simplon.devbookapi.repositories.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ResponseEntity<Object> postArticle(ArticleCreate input) throws IOException {
        try{
            ValidArticle validatedArticle = validArticle(input);
            createArticle(validatedArticle);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private ValidArticle validArticle(ArticleCreate input) throws IOException {
        Document doc = Jsoup.connect(input.url()).get();
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
        Elements author = doc.select(String.format("meta[property='%s1:authors']", contentType));
        String contentAuthor = author.attr("content");

        return checkArticle(contentUrl, contentTitle, contentImage, contentDescription, contentAuthor);
    }

    private static ValidArticle checkArticle(String contentUrl, String contentTitle, String contentImage, String contentDescription, String contentAuthor) {
        return new ValidArticle(
                contentUrl,
                contentTitle,
                contentImage,
                contentDescription,
                contentAuthor
        );
    }

    private void createArticle(ValidArticle articleValidated) {
        Article article = new Article();
        article.setUrl(articleValidated.url());
        article.setTitle(articleValidated.title());
        article.setImagePath(articleValidated.imagePath());
        article.setDescription(articleValidated.description());
        article.setAuthors(articleValidated.authors());
        articleRepository.save(article);
    }
}
