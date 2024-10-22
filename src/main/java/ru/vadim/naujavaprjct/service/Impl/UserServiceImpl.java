package ru.vadim.naujavaprjct.service.Impl;

import org.springframework.stereotype.Service;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.UserAlreadyExistsError;
import ru.vadim.naujavaprjct.exception.UserNotFoundError;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.service.UserService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(String username) throws UserAlreadyExistsError {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new UserAlreadyExistsError(username);
        } else {
            userRepository.save(new User(username, OffsetDateTime.now(), OffsetDateTime.now()));
        }
    }

    @Override
    public User findById(Long id) throws UserNotFoundError {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundError(id);
        }
    }

    @Override
    public void deleteById(Long id) throws UserNotFoundError {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundError(id);
        }
    }

    @Override
    public void updateUsername(Long id, String username) throws UserNotFoundError {
        if (userRepository.findByUsername(username).isPresent()) {
            userRepository.save(
                    new User(username, OffsetDateTime.now(), OffsetDateTime.now()));
        } else {
            throw new UserNotFoundError(id);
        }
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
