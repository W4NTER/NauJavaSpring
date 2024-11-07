package ru.vadim.naujavaprjct.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.dto.response.UserResponseDTO;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.service.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> findAllUsers() {
        return userService.listAll();
    }


    @PostMapping("/registration")
    public UserResponseDTO addUser(@RequestParam String username) {
        return userService.addUser(username);
    }

    @GetMapping("{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

}
