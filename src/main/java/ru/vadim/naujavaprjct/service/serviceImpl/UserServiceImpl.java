package ru.vadim.naujavaprjct.service.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.UserAlreadyExistsError;
import ru.vadim.naujavaprjct.exception.UserNotFoundError;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(Long id, String username) throws UserAlreadyExistsError {
            userRepository.create(new User(id, username));
    }

    @Override
    public User findById(Long id) throws UserNotFoundError {
        return userRepository.read(id).orElseThrow(() -> new UserNotFoundError(id));
    }

    @Override
    public void deleteById(Long id) throws UserNotFoundError {
        userRepository.delete(id);
    }

    @Override
    public void updateUsername(Long id, String username) {
        userRepository.update(new User(id, username));
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
