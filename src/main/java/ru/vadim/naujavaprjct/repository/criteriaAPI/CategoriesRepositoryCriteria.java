package ru.vadim.naujavaprjct.repository.criteriaAPI;

import ru.vadim.naujavaprjct.entity.Categories;

public interface CategoriesRepositoryCriteria {
    Categories findCategoryByOperationId(Long operationId);
}
