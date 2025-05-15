package co.simplon.devbookapi.services;

import co.simplon.devbookapi.dtos.ArticleCreate;
import co.simplon.devbookapi.dtos.ShareArticleCreate;
import co.simplon.devbookapi.dtos.ValidArticle;
import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.entities.Article;
import co.simplon.devbookapi.entities.ShareArticle;
import co.simplon.devbookapi.repositories.AccountRepository;
import co.simplon.devbookapi.repositories.ArticleRepository;
import co.simplon.devbookapi.repositories.ShareArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ShareArticleRepository shareArticleRepository;
    private final AccountRepository accountRepository;
    private final NotificationService notificationService;

    public ArticleService(ArticleRepository articleRepository, ShareArticleRepository shareArticleRepository, AccountRepository accountRepository, NotificationService notificationService) {
        this.articleRepository = articleRepository;
        this.shareArticleRepository = shareArticleRepository;
        this.accountRepository = accountRepository;
        this.notificationService = notificationService;
    }

    public void postArticle(ArticleCreate input, String username) throws IOException {
        try{
            ValidArticle validatedArticle = validArticle(input);
            createArticle(validatedArticle);
            shareArticle(input, username);
            notificationService.sendNotif("RSS");
        }catch(IOException e){
            System.out.println("Cannot access to the article");
        }
    }

    private void shareArticle(ArticleCreate input, String username) {
        Account account = accountRepository.findByUsername(username);
        Article article = articleRepository.findByUrl(input.url());
        ShareArticleCreate shareArticleCreate = new ShareArticleCreate(
                account,
                article
        );
        ShareArticle shareArticle = new ShareArticle();
        shareArticle.setAccount(account);
        shareArticle.setArticle(article);
        shareArticle.setPublishedDate(LocalDateTime.now());
        shareArticleRepository.save(shareArticle);
    }

    private ValidArticle validArticle(ArticleCreate input) throws IOException {
        Document doc = Jsoup.connect(input.url()).get();
        Elements title = doc.select("meta[property='og:title']");
        String contentTitle = title.attr("content");
        Elements type = doc.select("meta[property='og:type']");
        String contentType = type.attr("content");
        Elements image = doc.select("meta[property='og:image']");
        String contentImage = image.attr("content");
        Elements description = doc.select("meta[property='og:description']");
        String contentDescription = description.attr("content");
        Elements author = doc.select(String.format("meta[property='%s1:authors']", contentType));
        String contentAuthor = author.attr("content");

        return checkArticle(input.url(), contentTitle, contentImage, contentDescription, contentAuthor);
    }

    private static ValidArticle checkArticle(String url, String contentTitle, String contentImage, String contentDescription, String contentAuthor) {
        return new ValidArticle(
                url,
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
