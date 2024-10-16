package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vadim.naujavaprjct.entity.Users;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<Users, Long> {
}
