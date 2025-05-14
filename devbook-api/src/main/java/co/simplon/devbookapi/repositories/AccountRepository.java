package co.simplon.devbookapi.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.dtos.ProfileDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByUsername(String username);

    Optional<Account> findAllByUsernameIgnoreCase(String username);

    Optional<Account> findById(Long id);

	ProfileDetails getNotifArticleAndNotifRssByUsername(String token);

	@Modifying
	@NativeQuery("UPDATE t_accounts as a SET notif_article = ?2, notif_rss = ?3 where a.username = ?1")
	void updateProfile(String username, boolean notifArticle, boolean notifRss);


	@Query("SELECT a.username FROM Account a WHERE a.notifArticle = true")
	List<String> getAccountUsernamesWithNotifArticle();


	@Query("SELECT a.username FROM Account a WHERE a.notifRss = true")
	List<String> getAccountUsernamesWithNotifRss();
}
