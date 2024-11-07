package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vadim.naujavaprjct.entity.Account;
import ru.vadim.naujavaprjct.entity.User;

import java.util.Optional;

@RepositoryRestResource(path = "account")
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserAndName(User user, String name);
    Optional<Account> findAccountByName(String name);
}
