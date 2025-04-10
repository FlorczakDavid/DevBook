package co.simplon.devbookapi.controllers;

import co.simplon.devbookapi.dtos.AccountCreate;
import co.simplon.devbookapi.dtos.AuthInfo;
import co.simplon.devbookapi.dtos.Authentication;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.simplon.devbookapi.services.AccountAuthenticateService;
import co.simplon.devbookapi.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    public final AccountService service;
    public final AccountAuthenticateService authService;

    public AccountController(AccountService service, AccountAuthenticateService authService) {
        this.service = service;
        this.authService = authService;
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
    AuthInfo authentificate(@RequestBody Authentication inputs) {
        return authService.authenticate(inputs);
    }
}