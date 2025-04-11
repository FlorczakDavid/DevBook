package co.simplon.devbookapi.repositories;

import co.simplon.devbookapi.entities.Article;
import co.simplon.devbookapi.entities.RssProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    boolean existsByUrl(String url);
}
