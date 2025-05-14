package co.simplon.devbookapi.validators;

import co.simplon.devbookapi.dtos.ShareArticleCreate;
import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.entities.Article;
import co.simplon.devbookapi.entities.ShareArticle;
import co.simplon.devbookapi.repositories.ArticleRepository;
import co.simplon.devbookapi.repositories.ShareArticleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class SharedArticleUniqueValidator implements ConstraintValidator<UrlUnique, ShareArticleCreate> {

    private final ShareArticleRepository shareArticleRepository;

    public SharedArticleUniqueValidator(ShareArticleRepository shareArticleRepository) {
        this.shareArticleRepository = shareArticleRepository;
    }

    @Override
    public boolean isValid(ShareArticleCreate shareArticle, ConstraintValidatorContext context) {
        Account account = shareArticle.account();
        Article article = shareArticle.article();
        return !shareArticleRepository.existsByAccountAndArticle(account, article);
    }
}
