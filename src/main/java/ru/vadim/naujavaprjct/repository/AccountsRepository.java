package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vadim.naujavaprjct.entity.Accounts;
import ru.vadim.naujavaprjct.entity.Users;


public interface AccountsRepository extends JpaRepository<Account, Long> {
    Account findByUserAndName(User user, String name);
}
