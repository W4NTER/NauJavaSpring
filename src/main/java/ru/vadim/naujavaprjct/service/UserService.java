package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.UserAlreadyExistsError;
import ru.vadim.naujavaprjct.exception.UserNotFoundError;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(String username) throws UserAlreadyExistsError;

    User findById(Long id) throws UserNotFoundError;

    void deleteById(Long id) throws UserNotFoundError;

    void updateUsername(Long id, String username) throws UserNotFoundError;

    List<User> listAll();
}
