package ru.practicum.event;

import org.springframework.stereotype.Service;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.repository.EventRepository;

import java.util.List;

@Service
public class EventServiceImpl {
    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) { //TODO: filtration
        this.eventRepository = eventRepository;
    }

    public List<EventShortDto> getAll() {
        return EventMapper.toEventShortDtos(eventRepository.findAll());
    }
}
