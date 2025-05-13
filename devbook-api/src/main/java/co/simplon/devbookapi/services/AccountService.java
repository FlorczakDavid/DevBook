package co.simplon.devbookapi.services;

import co.simplon.devbookapi.dtos.AccountCreate;
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
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepos;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepos;
    private final JavaMailSender mailSender;
    private final EmailConfirmationService emailConfirmationService;

    @Value("${co.simplon.devbook.email.from}")
    private String emailFrom;

    @Value("${co.simplon.devbook.urlEmailConfirmation}")
    private String urlEmailConfirmation;

    public AccountService(AccountRepository accountRepos,
                          PasswordEncoder passwordEncoder,
                          RoleRepository roleRepos,
                          JavaMailSender mailSender,
                          EmailConfirmationService emailConfirmationService) {
        this.accountRepos = accountRepos;
        this.passwordEncoder = passwordEncoder;
        this.roleRepos = roleRepos;
        this.mailSender = mailSender;
        this.emailConfirmationService = emailConfirmationService;
    }

    @Transactional
    public void create(AccountCreate inputs) {
        Account entity = new Account();
        entity.setUsername(inputs.username());
        entity.setPassword(passwordEncoder.encode(inputs.password()));
        entity.setRole(roleRepos.findByName("MEMBER"));
        entity.setStatusEmail(false); // ou statusEmail(false), selon votre modèle
        accountRepos.save(entity);
        emailConfirmationService.sendConfirmationEmail(entity);
    }

    public String getAccount() {
        return "Account";
    }
}
