package ru.vadim.naujavaprjct.controller;

import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.dto.CategoriesDTO;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.service.CategoriesService;
import ru.vadim.naujavaprjct.service.OperationsService;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService,
                                OperationsService operationsService) {
        this.categoriesService = categoriesService;
    }

    @DeleteMapping("/{category_id}")
    public void deleteCategory(
            @PathVariable("category_id") Long categoryId) {
        categoriesService.deleteCategory(categoryId);
    }

    @PostMapping
    public Categories addCategory(@RequestBody CategoriesDTO category) {
        return categoriesService.addCategory(category);
    }
}
