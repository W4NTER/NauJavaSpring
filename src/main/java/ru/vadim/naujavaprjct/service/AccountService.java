package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.request.AccountsRequestDTO;
import ru.vadim.naujavaprjct.dto.response.AccountsResponseDTO;
import ru.vadim.naujavaprjct.entity.Account;

public interface AccountService {
    Account findByUserAndName(Long userId, String name);

    Account addAccount(AccountsRequestDTO account);
}
