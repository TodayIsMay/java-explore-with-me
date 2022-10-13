package ru.practicum.explorewhithme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.practicum.explorewhithme.dto.UpdateCommentDto;
import ru.practicum.explorewhithme.exception.AccessException;
import ru.practicum.explorewhithme.exception.CommentNotFoundException;
import ru.practicum.explorewhithme.model.Comment;
import ru.practicum.explorewhithme.model.Event;
import ru.practicum.explorewhithme.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {
    private final CommentRepository commentRepository;
    private final EventService eventService;
    private final UserService userService;

    public Comment save(Comment comment) {
        userService.findById(comment.getAuthorId()); //если такого юзера нет - выбросится исключение
        Event event = eventService.findById(comment.getEventId());
        if (Objects.equals(event.getInitiatorId(), comment.getAuthorId())) {
            throw new AccessException("Писать комментарий на своё событие невозможно");
        }
        comment.setCreated(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public Comment update(UpdateCommentDto comment, Long userId) {
        if (!Objects.equals(comment.getAuthorId(), userId)) {
            throw new AccessException("Исправить чужой комментарий невозможно");
        }
        Comment updateComment = findById(comment.getId());
        updateComment.setText(comment.getText() == null ? "" : comment.getText());
        return commentRepository.save(updateComment);
    }

    public Comment findById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else throw new CommentNotFoundException(id);
    }

    public List<Comment> getCommentForAuthorOrEvent(Long authId, Long eventId, int from, int size) {
        Pageable page = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "created"));
        Specification<Comment> authorId = authId == null ? null : commentAuthorId(authId);
        Specification<Comment> evId = eventId == null ? null : commentEventId(eventId);
        Specification<Comment> spec = Specification.where(authorId).and(evId);
        return commentRepository.findAll(spec, page).toList();
    }

    public void deleteComment(Long commentId) {
        commentRepository.delete(findById(commentId));
    }

    private Specification<Comment> commentAuthorId(long authorId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("authorId"), authorId));
    }

    private Specification<Comment> commentEventId(long eventId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("eventId"), eventId));
    }
}
