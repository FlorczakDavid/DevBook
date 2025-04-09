package co.simplon.devbookapi.repositories;

import java.util.Optional;

import co.simplon.devbookapi.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByUsername(String username);

    Optional<Account> findAllByUsernameIgnoreCase(String username);
}
