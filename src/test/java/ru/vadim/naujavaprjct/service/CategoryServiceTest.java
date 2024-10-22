package ru.vadim.naujavaprjct.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.Category;
import ru.vadim.naujavaprjct.entity.Operation;
import ru.vadim.naujavaprjct.repository.CategoryRepository;
import ru.vadim.naujavaprjct.repository.OperationRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Test
    @Transactional
    @Rollback
    void testThatDeleteCategoryReturnedSucceed() {
        Category category = new Category();
        var categoryWithId = categoryRepository.save(category);

        Operation operation1 = new Operation();
        Operation operation2 = new Operation();

        operation1.setCategory(category);
        operation2.setCategory(category);

        operationRepository.save(operation1);
        operationRepository.save(operation2);

        int EXPECTED_COUNT_OPERATIONS = 2;
        assertEquals(EXPECTED_COUNT_OPERATIONS,
                operationRepository.findAllByCategory(categoryWithId).size());

        categoryService.deleteCategory(categoryWithId);
        int EXPECTED_COUNT_OPERATIONS_AFTER_DEL = 0;
        assertEquals(EXPECTED_COUNT_OPERATIONS_AFTER_DEL,
                operationRepository.findAllByCategory(categoryWithId).size());

        assertEquals(Optional.empty(), categoryRepository.findById(categoryWithId.getId()));
    }

    @Test
    void testThatDeleteCategoryThrowsException() {
        Category category = new Category();
        var categoryWithId = categoryRepository.save(category);

        Operation operation1 = new Operation();
        Operation operation2 = new Operation();

        operation1.setCategory(category);
        operation2.setCategory(category);

        operationRepository.save(operation1);
        operationRepository.save(operation2);

        var notSavedCategory = new Category();
        assertThrows(DataAccessException.class, () ->
                categoryService.deleteCategory(notSavedCategory));

        int EXPECTED_COUNT_OPERATIONS = 2;
        assertEquals(EXPECTED_COUNT_OPERATIONS,
                operationRepository.findAllByCategory(categoryWithId).size());
    }
}
