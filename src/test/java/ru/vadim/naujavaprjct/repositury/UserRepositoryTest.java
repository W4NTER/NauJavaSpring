package ru.vadim.naujavaprjct.repositury;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback
    void testThatFindUserByUsernameReturnedSucceed() {
        User user = new User();
        user.setUsername("user");
        var categoryWithId = userRepository.save(user);


        Optional<User> res = userRepository.findByUsername(user.getUsername());

        assertEquals(res.get().getId(), categoryWithId.getId());
    }

    @Test
    @Transactional
    @Rollback
    void testThatFindUserByUsernameReturnedEmpty() {
        User user = new User();
        user.setUsername("user");
        userRepository.save(user);


        Optional<User> res = userRepository.findByUsername("some user with long username");

        assertTrue(res.isEmpty());


    }
}
