package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.entity.User;

import java.util.List;

public interface UserService {
    User addUser(String username);

    User findById(Long id);

    void deleteById(Long id);

    void updateUsername(Long id, String username);

    List<User> listAll();
}
