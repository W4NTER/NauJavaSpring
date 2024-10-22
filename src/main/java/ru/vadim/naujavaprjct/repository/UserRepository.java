package ru.vadim.naujavaprjct.repository;

import ru.vadim.naujavaprjct.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void create(User user);

    Optional<User> read(Long userId);

    void update(User user);

    void delete(Long id);

    List<User> findAll();
}
