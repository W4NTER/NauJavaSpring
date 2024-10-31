package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.request.AccountRequestDTO;
import ru.vadim.naujavaprjct.dto.response.AccountResponseDTO;
import ru.vadim.naujavaprjct.entity.Account;

import java.util.List;

public interface AccountService {
    AccountResponseDTO findByUserAndName(Long userId, String accountName);

    AccountResponseDTO addAccount(AccountRequestDTO account);

    List<AccountResponseDTO> findAll();
}
