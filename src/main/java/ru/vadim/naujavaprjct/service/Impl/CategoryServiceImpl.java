package ru.vadim.naujavaprjct.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.vadim.naujavaprjct.dto.request.CategoryRequestDTO;
import ru.vadim.naujavaprjct.dto.response.CategoryResponseDTO;
import ru.vadim.naujavaprjct.entity.Category;
import ru.vadim.naujavaprjct.entity.Operation;
import ru.vadim.naujavaprjct.exception.EntityAlreadyExistsException;
import ru.vadim.naujavaprjct.exception.EntityNotFoundException;
import ru.vadim.naujavaprjct.exception.UserNotFoundException;
import ru.vadim.naujavaprjct.repository.CategoryRepository;
import ru.vadim.naujavaprjct.repository.OperationRepository;
import ru.vadim.naujavaprjct.repository.UserRepository;
import ru.vadim.naujavaprjct.service.CategoryService;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final OperationRepository operationRepository;
    private final PlatformTransactionManager transactionManager;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               OperationRepository operationRepository,
                               PlatformTransactionManager transactionManager,
                               UserRepository userRepository, ObjectMapper objectMapper) {
        this.categoryRepository = categoryRepository;
        this.operationRepository = operationRepository;
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        TransactionStatus status = transactionManager
                .getTransaction(new DefaultTransactionDefinition());
        try {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                    new EntityNotFoundException(Category.class.getSimpleName()));
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

    @Override
    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRepository.findCategoriesByType(categoryRequestDTO.type()).isPresent()) {
            throw new EntityAlreadyExistsException(Category.class.getSimpleName());
        }
        Category category = categoryRepository.save(new Category(
                categoryRequestDTO.type(),
                categoryRequestDTO.title(),
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                userRepository.findById(categoryRequestDTO.userId()).orElseThrow(() ->
                        new UserNotFoundException(categoryRequestDTO.userId()))
        ));
        return objectMapper.convertValue(category, CategoryResponseDTO.class);
    }

//    private Long getUserId() {
//        var auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//        return userRepository.findByUsername(username).get().getId();
//    }
}
