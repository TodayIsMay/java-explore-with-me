package ru.practicum.exception;

public class EntityIsAlreadyExistsException extends RuntimeException {

    public EntityIsAlreadyExistsException(String message) {
        super(message);
    }
}
