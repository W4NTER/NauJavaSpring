package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vadim.naujavaprjct.entity.Users;


public interface UserRepository extends JpaRepository<Users, Long> {
}
