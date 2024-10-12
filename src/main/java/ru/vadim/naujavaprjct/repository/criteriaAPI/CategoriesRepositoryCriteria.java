package ru.vadim.naujavaprjct.repository.criteriaAPI;

import ru.vadim.naujavaprjct.entity.Categories;

import java.util.Optional;

public interface CategoriesRepositoryCriteria {
    Optional<Categories> findCategoryByOperationId(Long operationId);
}
