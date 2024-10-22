package ru.vadim.naujavaprjct.controller;

import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.dto.request.CategoriesRequestDTO;
import ru.vadim.naujavaprjct.dto.response.CategoriesResponseDTO;
import ru.vadim.naujavaprjct.entity.Category;
import ru.vadim.naujavaprjct.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @DeleteMapping("/{category_id}")
    public void deleteCategory(
            @PathVariable("category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @PostMapping
    public CategoriesResponseDTO addCategory(@RequestBody CategoriesRequestDTO category) {
        return castCategoryToResponseDTO(categoryService.addCategory(category));
    }

    private CategoriesResponseDTO castCategoryToResponseDTO(Category category) {
        return new CategoriesResponseDTO(
                category.getId(),
                category.getType(),
                category.getTitle(),
                category.getCreatedAt(),
                category.getUpdatedAt(),
                category.getUser().getId()
        );
    }
}
