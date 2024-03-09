package ru.accountservic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.accountservic.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
