package com.fw.coffeekubalpos.repositories;

import com.fw.coffeekubalpos.entities.Transaction;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
