package ru.practicum.event;

import ru.practicum.event.dto.EventShortDto;

import java.util.List;

public interface EventService {

    List<EventShortDto> getAll();

    EventShortDto getEventById(long id);

    EventShortDto postEvent(long userId, EventCreateRequest eventRequest);
}
