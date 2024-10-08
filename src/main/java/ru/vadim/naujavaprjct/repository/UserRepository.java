package ru.vadim.naujavaprjct.repository;

import ru.vadim.naujavaprjct.entity.User;

import java.util.List;

public interface UserRepository {
    void create(User user);

    User read(Long userId);

    void update(User user);

    void delete(Long id);

    List<User> findAll();
}
