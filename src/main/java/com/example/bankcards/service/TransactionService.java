package com.example.bankcards.service;

import com.example.bankcards.entity.Account;
import com.example.bankcards.entity.Transaction;
import com.example.bankcards.exception.NotFoundException;
import com.example.bankcards.repository.AccountRepository;
import com.example.bankcards.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;

    public TransactionService(TransactionRepository transactionRepo,
                              AccountRepository accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    public List<Transaction> getAll() {
        return transactionRepo.findAll();
    }

    public Transaction transfer(Long fromId, Long toId, BigDecimal amount) {
        Account from = accountRepo.findById(fromId)
                .orElseThrow(() -> new NotFoundException("Account not found: " + fromId));
        Account to = accountRepo.findById(toId)
                .orElseThrow(() -> new NotFoundException("Account not found: " + toId));

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        Transaction tx = new Transaction(from, to, amount, LocalDateTime.now());
        accountRepo.save(from);
        accountRepo.save(to);
        return transactionRepo.save(tx);
    }
}
