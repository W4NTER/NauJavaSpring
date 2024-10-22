package ru.vadim.naujavaprjct.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.dto.request.AccountsRequestDTO;
import ru.vadim.naujavaprjct.dto.response.AccountsResponseDTO;
import ru.vadim.naujavaprjct.entity.Account;
import ru.vadim.naujavaprjct.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    private static final Logger LOGGER = LogManager.getLogger();
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{account_name}")
    public AccountsResponseDTO findAccountByUserAndName(
            @PathVariable("account_name") String accountName, @RequestBody Long userId) {
         return castAccountToResponseDTO(accountService.findByUserAndName(userId, accountName));
    }

    @PostMapping("/add")
    public AccountsResponseDTO addAccount(@RequestBody AccountsRequestDTO requestDTO) {
        LOGGER.info(requestDTO.toString());
        return castAccountToResponseDTO(accountService.addAccount(requestDTO));
    }

    private AccountsResponseDTO castAccountToResponseDTO(Account account) {
        return new AccountsResponseDTO(
                account.getId(),
                account.getName(),
                account.getBalance(),
                account.getCreatedAt(),
                account.getUpdatedAt(),
                account.getUser().getId()
        );
    }
}
