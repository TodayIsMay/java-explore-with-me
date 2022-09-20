package ru.practicum.request;

public interface RequestService {
    RequestDto createRequest(long eventId, long userId);
}
