package co.simplon.devbookapi.dtos;

import co.simplon.devbookapi.validators.LinkUnique;

public record RssUrlCreate(
        @LinkUnique String url
) {
}
