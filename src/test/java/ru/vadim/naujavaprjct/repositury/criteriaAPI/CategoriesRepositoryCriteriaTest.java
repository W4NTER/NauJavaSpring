package ru.vadim.naujavaprjct.repositury.criteriaAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.entity.Operations;
import ru.vadim.naujavaprjct.repository.CategoriesRepository;
import ru.vadim.naujavaprjct.repository.OperationsRepository;
import ru.vadim.naujavaprjct.repository.criteriaAPI.CategoriesRepositoryCriteria;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoriesRepositoryCriteriaTest {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private OperationsRepository operationsRepository;
    @Autowired
    private CategoriesRepositoryCriteria categoriesRepositoryCriteria;

    @Test
    @Transactional
    @Rollback
    void testThatFindCategoryByOperationIdReturnedSucceed() {
        Categories category = new Categories();
        var categoryWithId = categoriesRepository.save(category);

        Operations operation = new Operations();
        operation.setCategory(categoryWithId);
        var operationWithId = operationsRepository.save(operation);

        Optional<Categories> res = categoriesRepositoryCriteria.findCategoryByOperationId(operationWithId.getId());

        assertEquals(res.get().getId(), categoryWithId.getId());
    }

    @Test
    @Transactional
    @Rollback
    void testThatFindCategoryByOperationIdThrowsException() {
        Operations operation = new Operations();
        var operationWithId = operationsRepository.save(operation);

        assertThrows(EmptyResultDataAccessException.class, () ->
                categoriesRepositoryCriteria.findCategoryByOperationId(operationWithId.getId()));
    }

}
