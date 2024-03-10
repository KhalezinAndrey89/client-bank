package ru.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.authservice.dto.Account;

import java.util.Optional;

@FeignClient(name = "account-service", url = "http://localhost:8081/accounts")
public interface AccountClient {

    @GetMapping("/{id}")
    Optional<Account> getAccountById(@PathVariable("id") Long id);

    @PostMapping("/create-account")
    Long createAccount();

    @DeleteMapping("/delete-account/{id}")
    void deleteAccountById(@PathVariable("id") Long id);

}
