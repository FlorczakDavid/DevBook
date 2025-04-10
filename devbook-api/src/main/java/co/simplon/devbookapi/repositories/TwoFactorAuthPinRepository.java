package co.simplon.devbookapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.devbookapi.entities.TwoFactorAuthPin;

@Repository
public interface TwoFactorAuthPinRepository extends JpaRepository<TwoFactorAuthPin, Long>{

}
