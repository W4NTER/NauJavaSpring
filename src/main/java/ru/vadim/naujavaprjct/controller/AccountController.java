package ru.vadim.naujavaprjct.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.dto.request.AccountRequestDTO;
import ru.vadim.naujavaprjct.dto.response.AccountResponseDTO;
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

    @GetMapping("/{user_id}/{account_name}")
    public AccountResponseDTO findAccountByUserAndName(
            @PathVariable("account_name") String accountName, @PathVariable("user_id") Long userId) {
         return accountService.findByUserAndName(userId, accountName);
    }

    @PostMapping("/add")
    public AccountResponseDTO addAccount(@RequestBody AccountRequestDTO requestDTO) {
        LOGGER.info(requestDTO.toString());
        return accountService.addAccount(requestDTO);
    }
}
