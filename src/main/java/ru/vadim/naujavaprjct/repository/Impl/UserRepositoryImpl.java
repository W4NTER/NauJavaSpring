package ru.vadim.naujavaprjct.repository.Impl;

import org.springframework.stereotype.Repository;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.exception.UserAlreadyExistsError;
import ru.vadim.naujavaprjct.exception.UserNotFoundError;
import ru.vadim.naujavaprjct.repository.UserRepository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> userContainer;

    public UserRepositoryImpl() {
        this.userContainer = new ArrayList<>();
    }

    @Override
    public void create(String username) {
        Random random = new Random();
       userContainer.add(new User(random.nextLong(Long.MAX_VALUE), username));
    }

    @Override
    public Optional<User> read(Long userId) {
        return userContainer.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }

    @Override
    public void update(User user) {
        int index = findIndexUserById(user.getId());
        userContainer.set(index, user);
    }

    @Override
    public void delete(Long id) throws UserNotFoundError {
        int index = findIndexUserById(id);
        if (index == -1) {
            throw new UserNotFoundError(id);
        }
        userContainer.remove(index);
    }

    @Override
    public List<User> findAll() {
        return userContainer;
    }

    private int findIndexUserById(Long userId) {
        return IntStream.range(0, userContainer.size())
                .filter(i -> userContainer.get(i).getId().equals(userId))
                .findFirst().orElse(-1);
    }
}
