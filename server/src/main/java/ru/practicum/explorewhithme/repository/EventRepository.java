package ru.practicum.explorewhithme.repository;

import ru.practicum.explorewhithme.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor {
    List<Event> findByInitiatorId(Long initiatorId, Pageable page);

    @Query(" SELECT e FROM Event e " +
            "WHERE (upper(e.annotation) LIKE upper(concat('%', ?1, '%')) " +
            " or upper(e.description) LIKE upper(concat('%', ?1, '%')))")
    List<Event> search(String text);

}
