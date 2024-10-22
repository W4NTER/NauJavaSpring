package ru.vadim.naujavaprjct.service.Impl;

import org.springframework.stereotype.Service;
import ru.vadim.naujavaprjct.dto.request.OperationRequestDTO;
import ru.vadim.naujavaprjct.entity.Account;
import ru.vadim.naujavaprjct.entity.Category;
import ru.vadim.naujavaprjct.entity.Operation;
import ru.vadim.naujavaprjct.exception.EntityNotFoundException;
import ru.vadim.naujavaprjct.repository.AccountRepository;
import ru.vadim.naujavaprjct.repository.CategoryRepository;
import ru.vadim.naujavaprjct.repository.OperationRepository;
import ru.vadim.naujavaprjct.service.OperationsService;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationsService {
    private final OperationRepository operationRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    public OperationServiceImpl(OperationRepository operationRepository,
                                CategoryRepository categoryRepository,
                                AccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Operation> findAllByCategory(Long categoryId) {
        return operationRepository.findAllByCategory(
                categoryRepository.findById(categoryId).orElseThrow(() ->
                        new EntityNotFoundException(Category.class.getSimpleName())));
    }

    @Override
    public Operation addOperation(OperationRequestDTO operationRequestDTO) {
        return operationRepository.save(new Operation(
                operationRequestDTO.sum(),
                operationRequestDTO.comment(),
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                accountRepository.findById(operationRequestDTO.account_id()).orElseThrow(() ->
                        new EntityNotFoundException(Account.class.getSimpleName())),
                categoryRepository.findById(operationRequestDTO.category_id()).orElseThrow(() ->
                        new EntityNotFoundException(Category.class.getSimpleName()))
        ));
    }
}
