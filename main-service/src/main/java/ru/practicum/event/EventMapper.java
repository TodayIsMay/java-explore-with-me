package ru.practicum.event;

import ru.practicum.event.dto.EventShortDto;

import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    public static EventShortDto toEventShortDto (Event event) {
        return new EventShortDto(event.getId(),
                event.getDescription(),
                event.getAnnotation(),
                new EventShortDto.Category(
                        event.getCategory().getId(),
                        event.getCategory().getName()),
                event.getEventDate(),
                new EventShortDto.User(
                        event.getInitiator().getId(),
                        event.getInitiator().getName()),
                event.getTitle());
    }

    public static List<EventShortDto> toEventShortDtos(List<Event> events) {
        List <EventShortDto> result = new ArrayList<>();
        for (Event event : events) {
            result.add(toEventShortDto(event));
        }
        return result;
    }
}
