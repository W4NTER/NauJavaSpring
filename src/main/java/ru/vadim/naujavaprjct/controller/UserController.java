package ru.vadim.naujavaprjct.controller;

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
        return userService.listAll().stream().map(this::castUserToResponseDTO).toList();
    }

    @PostMapping("/{username}")
    public UserResponseDTO addUser(@PathVariable String username) {
        return castUserToResponseDTO(userService.addUser(username));
    }

    @GetMapping("{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return castUserToResponseDTO(userService.findById(id));
    }

    private UserResponseDTO castUserToResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
