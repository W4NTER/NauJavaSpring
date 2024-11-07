package ru.vadim.naujavaprjct.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.CustomErrorException;
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
    @Transactional
    public void updateUsername(Long id, String username) throws CustomErrorException {
        var userOpt = userRepository.findById(id);
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsError(username);
        } else if (userOpt.isEmpty()) {
            throw new UserNotFoundError(id);
        } else {
            User user = userOpt.get();
            user.setUsername(username);
            user.setUpdatedAt(OffsetDateTime.now());
            userRepository.save(user);
        }
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
