package ru.accountservic.service;

import org.springframework.stereotype.Service;
import ru.accountservic.entity.Account;
import ru.accountservic.repository.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }
}
