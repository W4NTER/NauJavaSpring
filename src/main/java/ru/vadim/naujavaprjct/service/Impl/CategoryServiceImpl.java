package ru.vadim.naujavaprjct.service.Impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.vadim.naujavaprjct.entity.Category;
import ru.vadim.naujavaprjct.entity.Operation;
import ru.vadim.naujavaprjct.repository.CategoryRepository;
import ru.vadim.naujavaprjct.repository.OperationRepository;
import ru.vadim.naujavaprjct.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final OperationRepository operationRepository;
    private final PlatformTransactionManager transactionManager;

    public CategoryServiceImpl(CategoryRepository categoryRepository, OperationRepository operationRepository, PlatformTransactionManager transactionManager) {
        this.categoryRepository = categoryRepository;
        this.operationRepository = operationRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteCategory(Category category) {
        TransactionStatus status = transactionManager
                .getTransaction(new DefaultTransactionDefinition());
        try {
            List<Operation> operations = operationRepository
                    .findAllByCategory(category);
            operationRepository.deleteAll(operations);

            categoryRepository.delete(category);

            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
