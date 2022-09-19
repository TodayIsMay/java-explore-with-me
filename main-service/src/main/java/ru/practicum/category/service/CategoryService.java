package ru.practicum.category.service;

import org.springframework.web.bind.annotation.RequestBody;
import ru.practicum.category.entitty.Category;
import ru.practicum.exception.EntityIsAlreadyExistsException;

import java.util.List;
import java.util.NoSuchElementException;

public interface CategoryService {
    Category createCategory(@RequestBody Category category);

    Category editCategory(Category category) throws IllegalArgumentException, EntityIsAlreadyExistsException,
            NoSuchElementException;

    List<Category> findAllCategories(Integer from, Integer size);

    void deleteCategory(long categoryId) throws NoSuchElementException;
}
