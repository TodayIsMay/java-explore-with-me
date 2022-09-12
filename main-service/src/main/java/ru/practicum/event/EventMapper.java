package ru.practicum.event;

import ru.practicum.event.dto.EventShortDto;

import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    public static EventShortDto toEventShortDto (Event event) {
        return new EventShortDto(event.getId(),
                event.getDescription(),
                event.getAnnotation(),
                //event.getCategory(),
                event.getEventDate(),
                //event.getInitiator(),
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
