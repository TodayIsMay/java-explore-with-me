package ru.practicum.user;

import ru.practicum.event.Event;
import ru.practicum.event.dto.EventShortDto;

public interface UserService {
    EventShortDto postEvent(long userId, Event event);
}
