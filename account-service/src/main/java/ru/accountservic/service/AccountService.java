package ru.accountservic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.accountservic.entity.Account;
import ru.accountservic.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Long createAccount() {
        return accountRepository.save(Account.builder()
                .accountNumber(UUID.randomUUID().toString())
                .balance(BigDecimal.valueOf(0))
                .build()).getId();
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Transactional
    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

}
