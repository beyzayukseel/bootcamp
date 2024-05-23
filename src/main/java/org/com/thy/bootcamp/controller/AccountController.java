package org.com.thy.bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.Account;
import org.com.thy.bootcamp.entity.Card;
import org.com.thy.bootcamp.model.AccountRequestDto;
import org.com.thy.bootcamp.service.AccountService;
import org.com.thy.bootcamp.util.SystemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<SystemResponse> createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        accountService.createAccount(accountRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SystemResponse( "Account created successfully!", 201, true));
    }

    @PostMapping("/cards/{accountId}")
    public ResponseEntity<Account> createCardForAccount(@PathVariable Long accountId, @RequestBody Card card) {
        try {
            Account account = accountService.createCardForAccount(accountId, card);
            return ResponseEntity.ok(account);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
