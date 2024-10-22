package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vadim.naujavaprjct.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
