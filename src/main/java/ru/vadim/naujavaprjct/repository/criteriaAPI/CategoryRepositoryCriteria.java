package ru.vadim.naujavaprjct.repository.criteriaAPI;

import ru.vadim.naujavaprjct.entity.Category;

import java.util.Optional;

public interface CategoryRepositoryCriteria {
    Optional<Category> findCategoryByOperationId(Long operationId);
}
