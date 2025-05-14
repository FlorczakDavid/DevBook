package co.simplon.devbookapi.dtos;

import java.time.LocalDateTime;


public record ValidatedShareArticle(
        Long accountId,
        Long articleId,
        LocalDateTime publishedDate
) {
}
