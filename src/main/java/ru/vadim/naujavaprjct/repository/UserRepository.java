package ru.vadim.naujavaprjct.repository;

import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.UserAlreadyExistsError;
import ru.vadim.naujavaprjct.exception.UserNotFoundError;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void create(User user) throws UserAlreadyExistsError;

    Optional<User> read(Long userId);

    void update(User user);

    void delete(Long id) throws UserNotFoundError;

    List<User> findAll();
}
