package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vadim.naujavaprjct.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
