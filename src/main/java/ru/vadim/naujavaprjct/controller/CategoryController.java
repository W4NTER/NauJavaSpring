package ru.vadim.naujavaprjct.controller;

import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.dto.request.CategoryRequestDTO;
import ru.vadim.naujavaprjct.dto.response.CategoryResponseDTO;
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

    @PostMapping("/add")
    public CategoryResponseDTO addCategory(@RequestBody CategoryRequestDTO category) {
        return categoryService.addCategory(category);
    }

}
