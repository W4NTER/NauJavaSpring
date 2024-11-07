package ru.vadim.naujavaprjct.repositury.criteriaAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.Account;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.repository.AccountRepository;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.repository.criteriaAPI.AccountRepositoryCriteria;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountRepositoryCriteriaTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepositoryCriteria accountRepositoryCriteria;

    @Test
    @Transactional
    @Rollback
    void testThatFindByUserAndNameReturnedSucceed() {
        Account account = new Account();
        User user = new User();
        userRepository.save(user);

        account.setUser(user);
        account.setName("Some name");
        accountRepository.save(account);

        Optional<Account> res = accountRepositoryCriteria.findByUserAndName(user, "Some name");

        assertNotNull(res);
        assertEquals(account.getName(), res.get().getName());
    }

    @Test
    @Transactional
    @Rollback
    void testThatFindByUserAndNameThrowsException() {
        Account account = new Account();
        User user = new User();
        userRepository.save(user);

        account.setUser(user);
        account.setName("diff name");
        accountRepository.save(account);

        assertThrows(EmptyResultDataAccessException.class, () ->
                accountRepositoryCriteria.findByUserAndName(user, "some name"));
    }
}
