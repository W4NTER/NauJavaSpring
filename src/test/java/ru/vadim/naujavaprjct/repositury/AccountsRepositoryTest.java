package ru.vadim.naujavaprjct.repositury;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vadim.naujavaprjct.entity.Accounts;
import ru.vadim.naujavaprjct.entity.Users;
import ru.vadim.naujavaprjct.repository.AccountsRepository;
import ru.vadim.naujavaprjct.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountsRepositoryTest {
    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testThatFindByUserAndNameReturnedSucceed() {
        Accounts account = new Accounts();
        Users user = new Users();
        userRepository.save(user);

        account.setUser(user);
        account.setName("Some name");
        accountsRepository.save(account);

        Accounts res = accountsRepository.findByUserAndName(user, "Some name");

        assertNotNull(res);
        assertEquals(account.getName(), res.getName());
    }
}
