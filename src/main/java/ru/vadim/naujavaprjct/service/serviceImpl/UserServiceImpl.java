package ru.vadim.naujavaprjct.service.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.service.UserService;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(Long id, String username) {
        userRepository.save(new User(username, OffsetDateTime.now(), OffsetDateTime.now()));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUsername(Long id, String username) {
        userRepository.save(
                new User(username, OffsetDateTime.now(), OffsetDateTime.now()));
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}