package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.CategoriesDTO;
import ru.vadim.naujavaprjct.entity.Categories;

public interface CategoriesService {
    void deleteCategory(Long categoryId);
    Categories addCategory(CategoriesDTO categories);
}
