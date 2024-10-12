package ru.vadim.naujavaprjct.repository.criteriaAPI;

import ru.vadim.naujavaprjct.entity.Accounts;
import ru.vadim.naujavaprjct.entity.Users;

import java.util.Optional;


public interface AccountsRepositoryCriteria {
    Optional<Accounts> findByUserAndName(Users user, String name);
}
