package ru.vadim.naujavaprjct.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.vadim.naujavaprjct.dto.request.UserRequestDTO;
import ru.vadim.naujavaprjct.dto.response.UserResponseDTO;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.UsernameAlreadyInUseException;
import ru.vadim.naujavaprjct.exception.UserNotFoundException;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.service.UserService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserResponseDTO addUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new UsernameAlreadyInUseException(username);
        } else {
            return objectMapper.convertValue(userRepository.save(
                    new User(username, OffsetDateTime.now(), OffsetDateTime.now()))
            ,UserResponseDTO.class);
        }
    }

    @Override
    public UserResponseDTO findById(Long id) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            return objectMapper.convertValue(user.get(), UserResponseDTO.class);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public void updateUsername(UserRequestDTO userRequestDTO) {
        if (userRepository.findByUsername(userRequestDTO.username()).isPresent()) {
            userRepository.save(
                    new User(
                            userRequestDTO.username(),
                            OffsetDateTime.now(),
                            OffsetDateTime.now()));
        } else {
            throw new UserNotFoundException(userRequestDTO.id());
        }
    }

    @Override
    public List<UserResponseDTO> listAll() {
        return userRepository.findAll().stream()
                .map(user -> objectMapper.convertValue(user, UserResponseDTO.class)).toList();
    }
}
