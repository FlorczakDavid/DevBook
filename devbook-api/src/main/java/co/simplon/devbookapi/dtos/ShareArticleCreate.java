package co.simplon.devbookapi.dtos;

import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.entities.Article;
import co.simplon.devbookapi.validators.SharedArticleUnique;

@SharedArticleUnique
public record ShareArticleCreate(
        Account account,
        Article article
) {
}
