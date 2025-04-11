package co.simplon.devbookapi.dtos;
import co.simplon.devbookapi.validators.LinkUnique;
import jakarta.validation.constraints.NotBlank;

import java.time.ZonedDateTime;

public record RssProviderCreate(
        @LinkUnique
        @NotBlank
        String url,
        @NotBlank
        String link,
        @NotBlank
        String title,
        @NotBlank
        String description,
        String imageUrl,
        ZonedDateTime lastUpdate
        ) {
        @Override
        public String toString() {
                return "RssProviderCreate{ " +
                        "url='" + url + '\'' +
                        ", link='" + link + '\'' +
                        ", title='" + title + '\'' +
                        ", lastUpdate=" + lastUpdate +
                        '}';
        }
}
