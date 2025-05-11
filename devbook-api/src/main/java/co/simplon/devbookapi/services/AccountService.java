package co.simplon.devbookapi.services;

import co.simplon.devbookapi.dtos.AccountCreate;
import co.simplon.devbookapi.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.simplon.devbookapi.config.JwtProvider;
import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.repositories.AccountRepository;


@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository repos;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepos;

    public AccountService(AccountRepository repos, PasswordEncoder passwordEncoder, JwtProvider jwtProvider,
                          RoleRepository roleRepos) {
        this.repos = repos;
        this.passwordEncoder = passwordEncoder;
        this.roleRepos = roleRepos;
    }

    @Transactional
    public void create(AccountCreate inputs) {
        Account entity = new Account();
        entity.setUsername(inputs.username());
        entity.setPassword(passwordEncoder.encode(inputs.password()));
        entity.setRole(roleRepos.findByName("MEMBER"));
        repos.save(entity);
    }


    public String getAccount() {
        return "Account";
    }
}