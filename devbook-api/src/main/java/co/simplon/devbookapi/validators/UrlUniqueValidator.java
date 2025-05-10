package co.simplon.devbookapi.validators;

import co.simplon.devbookapi.repositories.ArticleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UrlUniqueValidator implements ConstraintValidator<UrlUnique, String> {

    private final ArticleRepository articleRepository;

    public UrlUniqueValidator(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        String normalizedUrl = url.endsWith("/") ? url : url + "/";
        return !articleRepository.existsByUrl(normalizedUrl);
    }
}
