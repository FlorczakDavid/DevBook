package co.simplon.devbookapi.repositories;

import co.simplon.devbookapi.entities.EmailConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmation, Long> {
    Optional<EmailConfirmation> findByUuidToken(String uuidToken);
    void deleteByUuidToken(String uuidToken);
}
