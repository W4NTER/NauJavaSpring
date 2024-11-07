package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.request.UserRequestDTO;
import ru.vadim.naujavaprjct.dto.response.UserResponseDTO;
import ru.vadim.naujavaprjct.entity.User;

import java.util.List;

public interface UserService {
    UserResponseDTO addUser(String username, String password);

    UserResponseDTO findById(Long id);

    void deleteById(Long id);

    void updateUsername(UserRequestDTO userRequestDTO);

    List<UserResponseDTO> listAll();

    UserResponseDTO findByUsername(String username);
}
