package co.simplon.devbookapi.dtos;

import co.simplon.devbookapi.validators.UrlUnique;

public record ArticleCreate(
        @UrlUnique String url
) {
}
