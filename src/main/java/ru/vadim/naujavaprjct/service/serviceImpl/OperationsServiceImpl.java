package ru.vadim.naujavaprjct.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.dto.OperationsDTO;
import ru.vadim.naujavaprjct.entity.Operations;
import ru.vadim.naujavaprjct.repository.AccountsRepository;
import ru.vadim.naujavaprjct.repository.CategoriesRepository;
import ru.vadim.naujavaprjct.repository.OperationsRepository;
import ru.vadim.naujavaprjct.service.OperationsService;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OperationsServiceImpl implements OperationsService {
    private final OperationsRepository operationsRepository;
    private final CategoriesRepository categoriesRepository;
    private final AccountsRepository accountsRepository;

    public OperationsServiceImpl(OperationsRepository operationsRepository, CategoriesRepository categoriesRepository, AccountsRepository accountsRepository) {
        this.operationsRepository = operationsRepository;
        this.categoriesRepository = categoriesRepository;
        this.accountsRepository = accountsRepository;
    }

    @Override
    @Transactional
    public List<Operations> findAllByCategory(Long categoryId) {
        return operationsRepository.findAllByCategory(
                categoriesRepository.findById(categoryId).get());
    }

    @Override
    public Operations addOperation(OperationsDTO operationsDTO) {
        return operationsRepository.save(new Operations(
                operationsDTO.sum(),
                operationsDTO.comment(),
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                accountsRepository.findById(operationsDTO.account_id()).get(),
                categoriesRepository.findById(operationsDTO.category_id()).get()
        ));
    }
}
