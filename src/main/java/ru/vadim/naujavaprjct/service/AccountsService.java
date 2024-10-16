package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.AccountsDTO;
import ru.vadim.naujavaprjct.entity.Accounts;

import java.util.Optional;

public interface AccountsService {
    Optional<Accounts> findByUserAndName(Long userId, String name);

    Accounts addAccount(AccountsDTO account);
}
