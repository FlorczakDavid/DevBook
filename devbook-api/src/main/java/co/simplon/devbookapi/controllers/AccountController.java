package co.simplon.devbookapi.controllers;

import co.simplon.devbookapi.dtos.AccountCreate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.simplon.devbookapi.services.AccountService;

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


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    String getAccount() {
        return service.getAccount();
    }
}