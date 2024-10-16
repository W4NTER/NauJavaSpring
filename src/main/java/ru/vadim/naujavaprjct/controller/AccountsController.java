package ru.vadim.naujavaprjct.controller;

import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.dto.AccountsDTO;
import ru.vadim.naujavaprjct.entity.Accounts;
import ru.vadim.naujavaprjct.service.AccountsService;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    private final AccountsService accountsService;

    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping("/{account_name}")
    public Accounts findAccountByUserAndName(
            @PathVariable("account_name") String accountName, @RequestBody Long userId) {
        return accountsService.findByUserAndName(userId, accountName).get();
    }

    @PostMapping("/add")
    public Accounts addAccount(@RequestBody AccountsDTO accounts) {
        return accountsService.addAccount(accounts);
    }
}
