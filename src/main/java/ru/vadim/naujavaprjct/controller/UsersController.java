package ru.vadim.naujavaprjct.controller;

import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.entity.Users;
import ru.vadim.naujavaprjct.service.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> findAllUsers() {
        return userService.listAll();
    }

    @PostMapping("/{username}")
    public Users addUser(@PathVariable String username) {
        return userService.addUser(username);
    }

    @GetMapping("{id}")
    public Users getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}
