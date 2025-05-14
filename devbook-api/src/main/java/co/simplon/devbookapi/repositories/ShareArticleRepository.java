package co.simplon.devbookapi.repositories;

import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.entities.Article;
import co.simplon.devbookapi.entities.ShareArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareArticleRepository extends JpaRepository<ShareArticle, Long> {
    boolean existsByAccountAndArticle(Account account, Article article);
}
