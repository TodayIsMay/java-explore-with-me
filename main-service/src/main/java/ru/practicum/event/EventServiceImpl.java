package ru.practicum.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.State;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    EventRepository eventRepository;
    CategoryRepository categoryRepository;
    UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository,
                            UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<EventShortDto> getAll() {//TODO: filtration
        return EventMapper.toEventShortDtos(eventRepository.findAll());
    }

    @Override
    public EventShortDto getEventById(long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            log.warn("Событие с id {} не найдено!", id);
            throw new NoSuchElementException("Событие с таким id не найдено!");
        }
        return EventMapper.toEventShortDto(optionalEvent.get());
    }

    @Override
    public EventShortDto postEvent(long userId, EventCreateRequest eventRequest) {
        Event event = new Event();

        event.setAnnotation(eventRequest.getAnnotation());
        event.setCategory(categoryRepository.findById(eventRequest.getCategory()).get());
        event.setDescription(eventRequest.getDescription());
        event.setCreatedOn(LocalDateTime.now());
        event.setEventDate(eventRequest.getEventDate());
        event.setPaid(eventRequest.getPaid());
        event.setParticipantLimit(eventRequest.getParticipantLimit());
        event.setRequestModeration(eventRequest.getRequestModeration());
        event.setTitle(eventRequest.getTitle());
        event.setInitiator(userRepository.findById(userId).get());
        event.setState(State.PENDING);

        return EventMapper.toEventShortDto(eventRepository.save(event));
    }
}
