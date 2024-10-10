package ru.vadim.naujavaprjct.repository.criteriaAPI;

import ru.vadim.naujavaprjct.entity.Accounts;
import ru.vadim.naujavaprjct.entity.Users;

import java.util.List;

public interface AccountsRepositoryCriteria {
    List<Accounts> findByUserAndName(Users user, String name);
}
