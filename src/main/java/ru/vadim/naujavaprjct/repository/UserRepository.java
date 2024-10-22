package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vadim.naujavaprjct.entity.User;

import java.util.Optional;

@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
