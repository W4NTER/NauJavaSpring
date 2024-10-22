package ru.vadim.naujavaprjct.repository.criteriaAPI;

import ru.vadim.naujavaprjct.entity.Account;
import ru.vadim.naujavaprjct.entity.User;

import java.util.Optional;


public interface AccountRepositoryCriteria {
    Optional<Account> findByUserAndName(User user, String name);
}
