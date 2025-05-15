package co.simplon.devbookapi.services;

import co.simplon.devbookapi.dtos.AccountCreate;
import co.simplon.devbookapi.dtos.ProfileDetails;
import co.simplon.devbookapi.dtos.ProfileUpdate;
import co.simplon.devbookapi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.simplon.devbookapi.config.JwtProvider;
import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.repositories.AccountRepository;


@Service
@Transactional
public class AccountService {

    private final AccountRepository accounts;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RoleRepository roles;
    private final EmailConfirmationService emailConfirmationService;

    public AccountService(AccountRepository accounts, PasswordEncoder passwordEncoder, JwtProvider jwtProvider,
                          RoleRepository roles, EmailConfirmationService emailConfirmationService) {
        this.accounts = accounts;
        this.passwordEncoder = passwordEncoder;
        this.roles = roles;
        this.jwtProvider = jwtProvider;
        this.emailConfirmationService = emailConfirmationService;
    }

    @Transactional
    public void create(AccountCreate inputs) {
        Account entity = new Account();
        entity.setUsername(inputs.username());
        entity.setPassword(passwordEncoder.encode(inputs.password()));
        entity.setRole(roles.findByName("MEMBER"));
        entity.setNotifArticle(false);
        entity.setNotifRss(false);
        entity.setRole(roles.findByName("MEMBER"));
        entity.setStatusEmail(false);
        accounts.save(entity);
        emailConfirmationService.sendConfirmationEmail(entity);
    }

    public String getAccount() {
        return "Account";
    }

	public ProfileDetails getProfile(String token) {
		String username = jwtProvider.getSub(token);
		return accounts.getNotifArticleAndNotifRssByUsername(username);
	}

	public void updateProfile(ProfileUpdate inputs) {
		String username = jwtProvider.getSub(inputs.token());
		accounts.updateProfile(username, inputs.notifArticle(), inputs.notifRss());
	}
}
