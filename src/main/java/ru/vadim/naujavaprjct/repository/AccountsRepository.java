package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vadim.naujavaprjct.entity.Accounts;
import ru.vadim.naujavaprjct.entity.Users;

import java.util.Optional;

@RepositoryRestResource(path = "accounts")
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByUserAndName(Users users, String name);
}
