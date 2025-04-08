package co.simplon.devbookapi.validators;

import co.simplon.devbookapi.dtos.ArticleCreate;
import co.simplon.devbookapi.repositories.ArticleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UrlUniqueValidator implements ConstraintValidator<UrlUnique, ArticleCreate> {

    private final ArticleRepository articleRepository;

    public UrlUniqueValidator(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public boolean isValid(ArticleCreate input, ConstraintValidatorContext context) {
        String url = input.url();
        return !articleRepository.existsByUrl(url);
    }
}
