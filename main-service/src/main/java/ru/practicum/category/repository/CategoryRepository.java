package ru.practicum.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.category.entitty.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    @Query(nativeQuery = true, value = "WITH temp AS (SELECT category_id, name, ROW_NUMBER() OVER (ORDER BY category_id) " +
            "AS line_number FROM categories) SELECT * FROM temp WHERE line_number >= ? LIMIT ?")
    List<Category> findCategoriesFromTo(Integer from, Integer size);

    @Query(nativeQuery = true, value =  "SELECT * FROM categories LIMIT ?")
    List<Category> findAllLimit(Integer limit);
}
