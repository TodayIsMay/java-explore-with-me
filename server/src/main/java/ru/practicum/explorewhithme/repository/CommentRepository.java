package ru.practicum.explorewhithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import ru.practicum.explorewhithme.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor {
    List<Comment> findByAuthorId(Long authorId);

    List<Comment> findByEventId(Long eventId);
}
