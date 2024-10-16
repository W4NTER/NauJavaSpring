package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.entity.Users;

import java.util.List;

public interface UserService {
    Users addUser(String username);

    Users findById(Long id);

    void deleteById(Long id);

    void updateUsername(Long id, String username);

    List<Users> listAll();
}
