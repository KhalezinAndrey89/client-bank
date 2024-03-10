package ru.accountservic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.accountservic.dto.DepositRequest;
import ru.accountservic.entity.Account;
import ru.accountservic.service.AccountService;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public Optional<Account> getAccount(@PathVariable("id") Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping("/create-account")
    public ResponseEntity<Long> createAccount() {
        return ResponseEntity.ok(accountService.createAccount());
    }

    @DeleteMapping("/delete-account/{id}")
    public void deleteAccountById(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long accountId, @RequestBody DepositRequest amount) {
        Account updatedAccount = accountService.deposit(accountId, amount.getAmount());
        return ResponseEntity.ok(updatedAccount);
    }
}
