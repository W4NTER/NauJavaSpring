package ru.vadim.naujavaprjct.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.dto.AccountsDTO;
import ru.vadim.naujavaprjct.entity.Accounts;
import ru.vadim.naujavaprjct.repository.AccountsRepository;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.repository.criteriaAPI.AccountsRepositoryCriteria;
import ru.vadim.naujavaprjct.service.AccountsService;

import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepositoryCriteria accountsRepositoryCriteria;
    private final AccountsRepository accountsRepository;
    private final UserRepository userRepository;

    public AccountsServiceImpl(AccountsRepositoryCriteria accountsRepositoryCriteria, AccountsRepository accountsRepository, UserRepository userRepository) {
        this.accountsRepositoryCriteria = accountsRepositoryCriteria;
        this.accountsRepository = accountsRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Optional<Accounts> findByUserAndName(Long userId, String name) {
        return accountsRepositoryCriteria.findByUserAndName(
                userRepository.findById(userId).get(), name);
    }

    @Override
    public Accounts addAccount(AccountsDTO account) {
        return accountsRepository.save(new Accounts(
                account.name(),
                account.balance(),
                account.createdAt(),
                account.updatedAt(),
                userRepository.findById(account.userId()).get()
        ));
    }
}
