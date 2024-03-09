package ru.accountservic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.accountservic.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
