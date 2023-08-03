package com.dws.challenge.controller;


import com.dws.challenge.model.Account;
import com.dws.challenge.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public Account addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestParam Long accountFromId, @RequestParam Long accountToId, @RequestParam double amount) {
        accountService.transferMoney(accountFromId, accountToId, amount);
    }
}
