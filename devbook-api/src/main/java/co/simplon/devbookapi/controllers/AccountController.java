package co.simplon.devbookapi.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.simplon.devbookapi.dtos.AuthInfo;
import co.simplon.devbookapi.dtos.Authentication;
import co.simplon.devbookapi.services.AccountAuthenticateService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	  public final AccountAuthenticateService authService;

    public AccountController(AccountAuthenticateService authService) {
        this.authService = authService;
    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    void create(@RequestBody @Valid AccountCreate inputs) {
//        service.create(inputs);
//    }
//
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    AuthInfo authentificate(@RequestBody Authentication inputs) {
        return authService.authenticate(inputs);
    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    String getAccount() {
//        return service.getAccount();
//    }
}