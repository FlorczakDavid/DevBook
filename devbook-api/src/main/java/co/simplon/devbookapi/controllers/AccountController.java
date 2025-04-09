package co.simplon.devbookapi.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    public final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody @Valid AccountCreate inputs) {
        service.create(inputs);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    Object authentificate(@RequestBody AccountAuthentificate inputs) {
        return service.authentificate(inputs);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    String getAccount() {
        return service.getAccount();
    }
}