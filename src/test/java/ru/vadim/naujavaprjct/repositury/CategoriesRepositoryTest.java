package ru.vadim.naujavaprjct.repositury;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.entity.Operations;
import ru.vadim.naujavaprjct.repository.CategoriesRepository;
import ru.vadim.naujavaprjct.repository.OperationsRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CategoriesRepositoryTest {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private OperationsRepository operationsRepository;

    @Test
    @Transactional
    @Rollback
    void testThatFindCategoryByOperationIdReturnedSucceed() {
        Categories category = new Categories();
        var categoryWithId = categoriesRepository.save(category);

        Operations operation = new Operations();
        operation.setCategory(categoryWithId);
        var operationWithId = operationsRepository.save(operation);

        Optional<Categories> res = categoriesRepository.findCategoryByOperationId(operationWithId.getId());

        assertEquals(res.get().getId(), categoryWithId.getId());
    }

    @Test
    @Transactional
    @Rollback
    void testThatFindCategoryByOperationIdReturnedEmpty() {
        Operations operation = new Operations();
        var operationWithId = operationsRepository.save(operation);

        Optional<Categories> res = categoriesRepository.findCategoryByOperationId(operationWithId.getId());

        assertEquals(Optional.empty(), res);
    }
}
