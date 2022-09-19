package ru.practicum.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.category.entitty.Category;
import ru.practicum.category.service.CategoryService;
import ru.practicum.exception.EntityIsAlreadyExistsException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(@RequestParam(required = false) Integer from,
                                           @RequestParam(required = false, defaultValue = "10") Integer size) {
        return categoryService.findAllCategories(from, size);
    }

    @PostMapping("/admin/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PatchMapping("/admin/categories")
    public Category editCategory(@RequestBody Category category) throws IllegalArgumentException, EntityIsAlreadyExistsException,
            NoSuchElementException {
        return categoryService.editCategory(category);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public void deleteCategory(@PathVariable long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/test/diff/{number}")
    public Integer test(@PathVariable int number) {
        return number;
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleAlreadyExists(final EntityIsAlreadyExistsException e) {
        return new ResponseEntity<>(
                Map.of("error", e.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleNoSuchElement(final NoSuchElementException e) {
        return new ResponseEntity<>(
                Map.of("error", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleIllegalArgument(final IllegalArgumentException e) {
        return new ResponseEntity<>(
                Map.of("error", e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
