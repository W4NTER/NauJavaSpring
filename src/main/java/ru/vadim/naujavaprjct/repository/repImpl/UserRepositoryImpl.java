package ru.vadim.naujavaprjct.repository.repImpl;

import org.springframework.stereotype.Repository;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.repository.UserRepository;

import java.util.List;
import java.util.stream.IntStream;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> userContainer;

    public UserRepositoryImpl(List<User> userContainer) {
        this.userContainer = userContainer;
    }

    @Override
    public void create(User user) {
        userContainer.add(user);
    }

    @Override
    public User read(Long userId) {
        return userContainer.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst().orElse(null);
    }

    @Override
    public void update(User user) {
        int index = findIndexUserById(user.getId());
        userContainer.set(index, user);
    }

    @Override
    public void delete(Long id) {
        int index = findIndexUserById(id);
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
