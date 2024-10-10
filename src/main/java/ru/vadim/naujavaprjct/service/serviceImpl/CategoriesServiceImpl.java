package ru.vadim.naujavaprjct.service.serviceImpl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.entity.Operations;
import ru.vadim.naujavaprjct.repository.CategoriesRepository;
import ru.vadim.naujavaprjct.repository.OperationsRepository;
import ru.vadim.naujavaprjct.service.CategoriesService;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final OperationsRepository operationsRepository;
    private final PlatformTransactionManager transactionManager;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, OperationsRepository operationsRepository, PlatformTransactionManager transactionManager) {
        this.categoriesRepository = categoriesRepository;
        this.operationsRepository = operationsRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteCategory(Categories category) {
        TransactionStatus status = transactionManager
                .getTransaction(new DefaultTransactionDefinition());
        try {
            List<Operations> operations = operationsRepository
                    .findAllByCategory(category);
            operationsRepository.deleteAll(operations);

            categoriesRepository.delete(category);

            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
