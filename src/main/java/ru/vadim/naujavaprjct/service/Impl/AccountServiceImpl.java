package ru.vadim.naujavaprjct.service.Impl;

import org.springframework.stereotype.Service;
import ru.vadim.naujavaprjct.dto.request.AccountsRequestDTO;
import ru.vadim.naujavaprjct.entity.Account;
import ru.vadim.naujavaprjct.exception.EntityAlreadyExistsException;
import ru.vadim.naujavaprjct.exception.EntityNotFoundException;
import ru.vadim.naujavaprjct.exception.UserNotFoundException;
import ru.vadim.naujavaprjct.repository.AccountRepository;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.repository.criteriaAPI.AccountRepositoryCriteria;
import ru.vadim.naujavaprjct.service.AccountService;

import java.time.OffsetDateTime;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepositoryCriteria accountRepositoryCriteria;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepositoryCriteria accountRepositoryCriteria,
                              AccountRepository accountRepository,
                              UserRepository userRepository) {
        this.accountRepositoryCriteria = accountRepositoryCriteria;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Account findByUserAndName(Long userId, String name) {
        var user = userRepository.findById(userId);
        if (user.isPresent()) {
            return accountRepositoryCriteria.findByUserAndName(
                    user.get(), name).orElseThrow(() ->
                    new EntityNotFoundException(Account.class.getSimpleName()));
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    @Override
    public Account addAccount(AccountsRequestDTO account) {
        if (accountRepository.findAccountByName(account.name()).isPresent()) {
            throw new EntityAlreadyExistsException(Account.class.getSimpleName());
        }
        return accountRepository.save(new Account(
                account.name(),
                account.balance(),
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                userRepository.findById(account.userId()).orElseThrow(() ->
                        new UserNotFoundException(account.userId()))
        ));
    }
}
