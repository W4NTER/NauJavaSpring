package ru.vadim.naujavaprjct.service.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vadim.naujavaprjct.entity.Users;
import ru.vadim.naujavaprjct.entity.Users;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.service.UserService;

import java.time.OffsetDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(Long id, String username) {
        userRepository.save(new Users(username, OffsetDateTime.now(), OffsetDateTime.now()));
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUsername(Long id, String username) {
        userRepository.save(
                new Users(username, OffsetDateTime.now(), OffsetDateTime.now()));
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }
}
