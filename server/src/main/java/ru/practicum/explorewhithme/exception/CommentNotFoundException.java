package ru.practicum.explorewhithme.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("Комментарий с ID" + id + "не найден!");
    }
}
