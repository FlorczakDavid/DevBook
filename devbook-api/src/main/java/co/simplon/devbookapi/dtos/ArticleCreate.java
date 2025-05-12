package co.simplon.devbookapi.dtos;

import co.simplon.devbookapi.validators.UrlUnique;
import jakarta.validation.constraints.Pattern;

public record ArticleCreate(
        @UrlUnique
        @Pattern(regexp = "^(http|https)://[a-zA-Z0-9.]{1,253}\\.[a-zA-Z0-9/]{2,63}", message = "These URL is not valid")
        String url
) {
}
