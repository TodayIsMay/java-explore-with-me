package ru.practicum.user;


import org.springframework.stereotype.Service;
import ru.practicum.event.Event;
import ru.practicum.event.EventMapper;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.repository.EventRepository;

@Service
public class UserServiceImpl implements UserService {
    private final EventRepository eventRepository;

    public UserServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventShortDto postEvent(long userId, Event event) {
        return EventMapper.toEventShortDto(eventRepository.save(event));
    }
}
