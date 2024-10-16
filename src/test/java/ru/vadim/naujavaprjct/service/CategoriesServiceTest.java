package ru.vadim.naujavaprjct.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.entity.Operations;
import ru.vadim.naujavaprjct.repository.CategoriesRepository;
import ru.vadim.naujavaprjct.repository.OperationsRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoriesServiceTest {
    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private OperationsRepository operationsRepository;

    @Test
    @Transactional
    @Rollback
    void testThatDeleteCategoryReturnedSucceed() {
        Categories categories = new Categories();
        var categoryWithId = categoriesRepository.save(categories);

        Operations operation1 = new Operations();
        Operations operation2 = new Operations();

        operation1.setCategory(categories);
        operation2.setCategory(categories);

        operationsRepository.save(operation1);
        operationsRepository.save(operation2);

        int EXPECTED_COUNT_OPERATIONS = 2;
        assertEquals(EXPECTED_COUNT_OPERATIONS,
                operationsRepository.findAllByCategory(categoryWithId).size());

        categoriesService.deleteCategory(categoryWithId.getId());
        int EXPECTED_COUNT_OPERATIONS_AFTER_DEL = 0;
        assertEquals(EXPECTED_COUNT_OPERATIONS_AFTER_DEL,
                operationsRepository.findAllByCategory(categoryWithId).size());

        assertEquals(Optional.empty(), categoriesRepository.findById(categoryWithId.getId()));
    }

    @Test
    void testThatDeleteCategoryThrowsException() {
        Categories categories = new Categories();
        var categoryWithId = categoriesRepository.save(categories);

        Operations operation1 = new Operations();
        Operations operation2 = new Operations();

        operation1.setCategory(categories);
        operation2.setCategory(categories);

        operationsRepository.save(operation1);
        operationsRepository.save(operation2);

        var notSavedCategory = new Categories();
        assertThrows(DataAccessException.class, () ->
                categoriesService.deleteCategory(notSavedCategory.getId()));

        int EXPECTED_COUNT_OPERATIONS = 2;
        assertEquals(EXPECTED_COUNT_OPERATIONS,
                operationsRepository.findAllByCategory(categoryWithId).size());
    }
}
