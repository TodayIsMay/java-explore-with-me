package ru.practicum.request;

public class RequestMapper {
    public static RequestDto toRequestDto(Request request) {
        return new RequestDto(
                request.getId(),
                request.getStatus(),
                request.getCreated(),
                request.getRequester().getId(),
                request.getEvent().getId());
    }
}
