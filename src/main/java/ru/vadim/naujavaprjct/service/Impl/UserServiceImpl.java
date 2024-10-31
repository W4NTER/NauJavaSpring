package ru.vadim.naujavaprjct.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.dto.request.UserRequestDTO;
import ru.vadim.naujavaprjct.dto.response.UserResponseDTO;
import ru.vadim.naujavaprjct.entity.Authority;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.UsernameAlreadyInUseException;
import ru.vadim.naujavaprjct.exception.UserNotFoundException;
import ru.vadim.naujavaprjct.repository.AuthorityRepository;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.service.UserService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder encoder;
    private final AuthorityRepository authorityRepository;


    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper, PasswordEncoder encoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.encoder = encoder;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserResponseDTO addUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new UsernameAlreadyInUseException(username);
        } else {
            User user1 = userRepository.save(
                    new User(username, OffsetDateTime.now(), OffsetDateTime.now(), encoder.encode(password)));
            authorityRepository.save(new Authority("USER", user1));
            return objectMapper.convertValue(user1, UserResponseDTO.class);
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
        var userOpt = userRepository.findByUsername(userRequestDTO.username());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(userRequestDTO.username());
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    @Transactional
    public List<UserResponseDTO> listAll() {
        return userRepository.findAll().stream()
                .map(user -> objectMapper.convertValue(user, UserResponseDTO.class)).toList();
    }

    @Override
    public UserResponseDTO findByUsername(String username) {
        var userOpt =  userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            return objectMapper.convertValue(userOpt.get(), UserResponseDTO.class);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
