package ru.practicum.category.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.category.entitty.Category;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.exception.EntityIsAlreadyExistsException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) throws IllegalArgumentException, EntityIsAlreadyExistsException,
            NoSuchElementException {
        isValidCategory(category);
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(Category category) throws IllegalArgumentException, EntityIsAlreadyExistsException,
            NoSuchElementException {
        isValidCategory(category);
        Category editedCategory;
        if (categoryRepository.findById(category.getId()).isPresent()) {
            editedCategory = categoryRepository.findById(category.getId()).get();
        } else {
            log.warn("Категория с ID {} не найдена!", category.getId());
            throw new NoSuchElementException("Категория с таким ID не найдена!");
        }
        editedCategory.setName(category.getName());
        return categoryRepository.save(editedCategory);
    }

    @Override
    public List<Category> findAllCategories(Integer from, Integer size) {
        if(from == null) {
            return categoryRepository.findAllLimit(size);
        }
        System.out.println(categoryRepository.findCategoriesFromTo(from, size));
        return categoryRepository.findCategoriesFromTo(from, size);
    }

    @Override
    public void deleteCategory(long categoryId) throws NoSuchElementException {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            log.warn("Категории с ID {} не существует!", categoryId);
            throw new NoSuchElementException("Категории с таким ID не существует!");
        }
        categoryRepository.deleteById(categoryId);
    }

    private void isValidCategory(Category category) throws IllegalArgumentException, EntityIsAlreadyExistsException,
            NoSuchElementException {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            log.warn("Категория с именем уже {} существует!", category.getName());
            throw new EntityIsAlreadyExistsException("Категория с таким именем уже существует!");
        }
    }
}
