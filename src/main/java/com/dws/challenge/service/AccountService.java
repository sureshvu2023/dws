package com.dws.challenge.service;


import com.dws.challenge.model.Account;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {
    private final Map<Long, Account> accounts = new HashMap<>();
    public Account addAccount(Account account) {        // Add account implementation

        accounts.put(account.getId(), account);
        return account;
    }

    public Account getAccountById(Long accountId) {
        // Get account by ID implementation

        return accounts.get(accountId);
    }

    public synchronized  void transferMoney(Long accountFromId, Long accountToId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should be a positive number.");
        }

        Account accountFrom = accounts.get(accountFromId);
        Account accountTo = accounts.get(accountToId);

        if (accountFrom == null || accountTo == null) {
            throw new IllegalArgumentException("Invalid account IDs provided.");
        }

        if (accountFrom.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds for the transfer.");
        }

        // Perform the transfer
        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);


    }
    public void initSampleData() {
        // Sample data for accounts
        Account account1 = new Account(1L, 1000.0);
        Account account2 = new Account(2L, 500.0);

        accounts.put(account1.getId(), account1);
        accounts.put(account2.getId(), account2);
    }
}
