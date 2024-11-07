package ru.vadim.naujavaprjct.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.vadim.naujavaprjct.dto.request.AccountRequestDTO;
import ru.vadim.naujavaprjct.dto.response.AccountResponseDTO;
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
    private final ObjectMapper objectMapper;

    public AccountServiceImpl(AccountRepositoryCriteria accountRepositoryCriteria,
                              AccountRepository accountRepository,
                              UserRepository userRepository, ObjectMapper objectMapper) {
        this.accountRepositoryCriteria = accountRepositoryCriteria;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public AccountResponseDTO findByUserAndName(Long userId, String accountName) {
        var user = userRepository.findById(userId);
        if (user.isPresent()) {
            Account account = accountRepositoryCriteria.findByUserAndName(
                    user.get(), accountName).orElseThrow(() ->
                    new EntityNotFoundException(Account.class.getSimpleName()));
            return objectMapper.convertValue(account, AccountResponseDTO.class);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    @Override
    public AccountResponseDTO addAccount(AccountRequestDTO account) {
        if (accountRepository.findAccountByName(account.name()).isPresent()) {
            throw new EntityAlreadyExistsException(Account.class.getSimpleName());
        }
        return objectMapper.convertValue(accountRepository.save(new Account(
                account.name(),
                account.balance(),
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                userRepository.findById(account.userId()).orElseThrow(() ->
                        new UserNotFoundException(account.userId()))
        )), AccountResponseDTO.class);
    }
}
