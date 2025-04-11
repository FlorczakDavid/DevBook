package co.simplon.devbookapi.validators;

import co.simplon.devbookapi.repositories.RssProviderRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LinkUniqueValidator implements ConstraintValidator<LinkUnique, String> {

    private final RssProviderRepository rssProviderRepository;

    public LinkUniqueValidator(RssProviderRepository rssProviderRepository) {
        this.rssProviderRepository = rssProviderRepository;
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        if (url == null) {
            return true; // Ne pas valider si le champ est null (géré par @NotNull si nécessaire)
        }
       return !rssProviderRepository.existsByUrl(url);
    }
}
