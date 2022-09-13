package ru.practicum.category.service;

import org.springframework.web.bind.annotation.RequestBody;
import ru.practicum.category.entitty.Category;

public interface CategoryService {
    Category createCategory(@RequestBody Category category);
}
