package co.simplon.devbookapi.repositories;

import co.simplon.devbookapi.entities.RssProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RssProviderRepository extends JpaRepository<RssProvider, Long> {
    Optional<RssProvider> findByLink(String link);
    boolean existsByUrl(String url);
}
