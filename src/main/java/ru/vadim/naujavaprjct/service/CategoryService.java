package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.request.CategoriesRequestDTO;
import ru.vadim.naujavaprjct.entity.Category;

public interface CategoryService {
    void deleteCategory(Long categoryId);
    Category addCategory(CategoriesRequestDTO categories);
}
