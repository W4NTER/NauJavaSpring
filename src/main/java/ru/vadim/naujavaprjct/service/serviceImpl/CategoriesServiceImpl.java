package ru.vadim.naujavaprjct.service.serviceImpl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.vadim.naujavaprjct.dto.CategoriesDTO;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.entity.Operations;
import ru.vadim.naujavaprjct.repository.CategoriesRepository;
import ru.vadim.naujavaprjct.repository.OperationsRepository;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.service.CategoriesService;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final OperationsRepository operationsRepository;
    private final PlatformTransactionManager transactionManager;
    private final UserRepository userRepository;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, OperationsRepository operationsRepository, PlatformTransactionManager transactionManager, UserRepository userRepository) {
        this.categoriesRepository = categoriesRepository;
        this.operationsRepository = operationsRepository;
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        TransactionStatus status = transactionManager
                .getTransaction(new DefaultTransactionDefinition());
        try {
            Categories category = categoriesRepository.findById(categoryId).get();
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

    @Override
    public Categories addCategory(CategoriesDTO categories) {
        return categoriesRepository.save(new Categories(
                categories.type(),
                categories.title(),
                categories.createdAt(),
                categories.updatedAt(),
                userRepository.findById(categories.userId()).get()
        ));
    }
}
