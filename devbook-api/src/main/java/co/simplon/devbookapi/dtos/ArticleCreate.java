package co.simplon.devbookapi.dtos;

import co.simplon.devbookapi.validators.UrlUnique;
import jakarta.validation.constraints.Pattern;

public record ArticleCreate(
        @UrlUnique
        @Pattern(regexp = "^https://([a-zA-Z0-9]{1,63}|[a-zA-Z0-9]{1}[a-zA-Z0-9-]{0,61}[a-zA-Z0-9]{1})(\\.([a-zA-Z0-9]{1,63}|[a-zA-Z0-9]{1}[a-zA-Z0-9-]{0,61}[a-zA-Z0-9]{1})){0,3}\\.([a-zA-Z0-9]{2,63}|[a-zA-Z0-9]{1}[a-zA-Z0-9-]{0,61}[a-zA-Z0-9]{1})", message = "These URL is not valid")
        String url
) {
}
