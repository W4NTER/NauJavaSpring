package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.request.CategoryRequestDTO;
import ru.vadim.naujavaprjct.dto.response.CategoryResponseDTO;

public interface CategoryService {
    void deleteCategory(Long categoryId);
    CategoryResponseDTO addCategory(CategoryRequestDTO categories);
}
