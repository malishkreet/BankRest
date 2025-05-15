package com.example.bankcards.service;

import com.example.bankcards.entity.Account;
import com.example.bankcards.exception.NotFoundException;
import com.example.bankcards.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepo;

    public AccountService(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public List<Account> listByUser(Long userId) {
        return accountRepo.findByOwnerId(userId);
    }

    public Account getById(Long id) {
        return accountRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found with id: " + id));
    }

    public Account create(Account acc) {
        return accountRepo.save(acc);
    }

    public Account deposit(Long id, BigDecimal amount) {
        Account a = getById(id);
        a.setBalance(a.getBalance().add(amount));
        return accountRepo.save(a);
    }

    public Account withdraw(Long id, BigDecimal amount) {
        Account a = getById(id);
        a.setBalance(a.getBalance().subtract(amount));
        return accountRepo.save(a);
    }

    public void delete(Long id) {
        if (!accountRepo.existsById(id)) {
            throw new NotFoundException("Account not found with id: " + id);
        }
        accountRepo.deleteById(id);
    }
}
