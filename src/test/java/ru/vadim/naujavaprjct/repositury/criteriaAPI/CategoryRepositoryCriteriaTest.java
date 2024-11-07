package ru.vadim.naujavaprjct.repositury.criteriaAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.Category;
import ru.vadim.naujavaprjct.entity.Operation;
import ru.vadim.naujavaprjct.repository.CategoryRepository;
import ru.vadim.naujavaprjct.repository.OperationRepository;
import ru.vadim.naujavaprjct.repository.criteriaAPI.CategoryRepositoryCriteria;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryRepositoryCriteriaTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private CategoryRepositoryCriteria categoryRepositoryCriteria;

    @Test
    @Transactional
    @Rollback
    void testThatFindCategoryByOperationIdReturnedSucceed() {
        Category category = new Category();
        var categoryWithId = categoryRepository.save(category);

        Operation operation = new Operation();
        operation.setCategory(categoryWithId);
        var operationWithId = operationRepository.save(operation);

        Optional<Category> res = categoryRepositoryCriteria.findCategoryByOperationId(operationWithId.getId());

        assertEquals(res.get().getId(), categoryWithId.getId());
    }

    @Test
    @Transactional
    @Rollback
    void testThatFindCategoryByOperationIdThrowsException() {
        Operation operation = new Operation();
        var operationWithId = operationRepository.save(operation);

        assertThrows(EmptyResultDataAccessException.class, () ->
                categoryRepositoryCriteria.findCategoryByOperationId(operationWithId.getId()));
    }

}
