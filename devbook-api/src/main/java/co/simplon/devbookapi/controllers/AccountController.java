package co.simplon.devbookapi.controllers;

import co.simplon.devbookapi.dtos.AccountCreate;
import co.simplon.devbookapi.dtos.AuthInfo;
import co.simplon.devbookapi.dtos.Authentication;
import co.simplon.devbookapi.dtos.ProfileUpdate;
import co.simplon.devbookapi.dtos.EmailConfirmationInfo;
import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.entities.EmailConfirmation;
import co.simplon.devbookapi.repositories.AccountRepository;
import co.simplon.devbookapi.repositories.EmailConfirmationRepository;
import co.simplon.devbookapi.services.EmailConfirmationService;
import co.simplon.devbookapi.dtos.EmailConfirmationInfo;
import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.entities.EmailConfirmation;
import co.simplon.devbookapi.repositories.AccountRepository;
import co.simplon.devbookapi.repositories.EmailConfirmationRepository;
import co.simplon.devbookapi.services.EmailConfirmationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.simplon.devbookapi.services.AccountAuthenticateService;
import co.simplon.devbookapi.services.AccountService;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    public final AccountService service;
    public final AccountAuthenticateService authService;
    private final EmailConfirmationRepository emailConfirmationRepository;
    private final AccountRepository accountRepository;
    private final EmailConfirmationService emailConfirmationService;
 
    
    

    public AccountController(AccountService service,
                             AccountAuthenticateService authService,
                             EmailConfirmationRepository emailConfirmationRepository,
                             AccountRepository accountRepository, EmailConfirmationService emailConfirmationService
         AccountRepository accountRepository, EmailConfirmationService emailConfirmationService) {
                    
        this.service = service;
        this.authService = authService;
        this.emailConfirmationRepository = emailConfirmationRepository;
        this.accountRepository = accountRepository;
        this.emailConfirmationService = emailConfirmationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody @Valid AccountCreate inputs) {
       service.create(inputs);
       
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    String getAccount() {
        return service.getAccount();
    }
    
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void authentificate(@RequestBody Authentication inputs) {
        authService.authenticate(inputs);
    }
    
    @PostMapping("/doubleAuth/{token}")
    @ResponseStatus(HttpStatus.CREATED)
    AuthInfo verifyPin(@PathVariable("token") String token, @RequestBody String pin) {
    	return authService.verifyPin(pin, token);
    }
    
    @GetMapping("/profile/{token}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Object getProfile(@PathVariable("token") String token) {
    	return service.getProfile(token);
    }
    
    @PatchMapping("/updateProfile")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateProfile(@RequestBody ProfileUpdate inputs) {
    	service.updateProfile(inputs);
    }
    

    @GetMapping("/confirm/{uuidToken}")
    public ResponseEntity<String> confirmEmail(@PathVariable String uuidToken) {
        EmailConfirmation emailConfirmation = emailConfirmationRepository.findByUuidToken(uuidToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token perdu"));

        if (emailConfirmation.getExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expiré");
        }

        Account account = emailConfirmation.getAccount();
        account.setStatusEmail(true);
        accountRepository.save(account);
        emailConfirmationRepository.deleteByUuidToken(uuidToken);

        return ResponseEntity.ok("eMail ok");
    }

    @GetMapping("/confirm/{uuidToken}")
    public ResponseEntity<String> confirmEmail(@PathVariable String uuidToken) {
        EmailConfirmation emailConfirmation = emailConfirmationRepository.findByUuidToken(uuidToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token perdu"));

        if (emailConfirmation.getExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expiré");
        }

        Account account = emailConfirmation.getAccount();
        account.setStatusEmail(true);
        accountRepository.save(account);
        emailConfirmationRepository.deleteByUuidToken(uuidToken);

        return ResponseEntity.ok("eMail ok");
    }
}