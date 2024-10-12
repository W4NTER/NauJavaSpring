package ru.vadim.naujavaprjct.repositury;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.Accounts;
import ru.vadim.naujavaprjct.entity.Users;
import ru.vadim.naujavaprjct.repository.AccountsRepository;
import ru.vadim.naujavaprjct.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountsRepositoryTest {
    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @Rollback
    void testThatFindByUserAndNameReturnedSucceed() {
        Accounts account = new Accounts();
        Users user = new Users();
        userRepository.save(user);

        account.setUser(user);
        account.setName("Some name");
        accountsRepository.save(account);

        Optional<Accounts> res = accountsRepository.findByUserAndName(user, "Some name");

        assertNotNull(res);
        assertEquals(account.getName(), res.get().getName());
    }

    @Test
    @Transactional
    @Rollback
    void testThatFindByUserAndNameReturnedEmpty() {
        Accounts account = new Accounts();
        Users user = new Users();
        userRepository.save(user);

        account.setUser(user);
        account.setName("diff name");
        accountsRepository.save(account);

        Optional<Accounts> res = accountsRepository.findByUserAndName(user, "some name");

        assertEquals(Optional.empty(), res);
    }
}
