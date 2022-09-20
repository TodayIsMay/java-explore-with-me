package ru.practicum.compilation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.event.Event;
import ru.practicum.event.EventService;
import ru.practicum.event.repository.EventRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;
    private final EventService eventService;

    public CompilationServiceImpl(CompilationRepository compilationRepository, EventRepository eventRepository,
                                  EventService eventService) {
        this.compilationRepository = compilationRepository;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
    }

    @Override
    public CompilationDto createCompilation(NewCompilationDto newCompilationDto) {
        List<Event> events = newCompilationDto.getEvents().stream()
                .map(event -> eventRepository.findById(event.longValue()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        Compilation compilation = new Compilation();
        compilation.setEvents(events);
        compilation.setPinned(newCompilationDto.isPinned());
        compilation.setTitle(newCompilationDto.getTitle());

        return CompilationMapper.toCompilationDto(compilationRepository.save(compilation));
    }

    @Override
    public void deleteEventFromCompilation(long compilationId, long eventId) {
        Optional<Compilation> optionalCompilation = compilationRepository.findById(compilationId);
        Compilation compilation;
        if (optionalCompilation.isPresent()) {
            compilation = optionalCompilation.get();
            List<Event> events = compilation.getEvents();
            Event target = null;
            for (Event event : events) {
                if (event.getId() == eventId) {
                    target = event;
                }
            }
            events.remove(target);
            compilation.setEvents(events);
            compilationRepository.save(compilation);
        }
    }

    @Override
    public CompilationDto addEventToCompilation(long compilationId, long eventId) throws NoSuchElementException {
        Compilation compilation = getCompilationById(compilationId);
        List<Event> events = compilation.getEvents();
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            log.warn("Событие с ID {} не найдено!", eventId);
            throw new NoSuchElementException("Событие с таким ID не найдено!");
        }
        events.add(optionalEvent.get());
        compilation.setEvents(events);
        return CompilationMapper.toCompilationDto(compilationRepository.save(compilation));
    }

    @Override
    public Compilation getCompilationById(long compilationId) throws NoSuchElementException {
        Optional<Compilation> optionalCompilation = compilationRepository.findById(compilationId);
        if (optionalCompilation.isEmpty()) {
            log.warn("Подборка с ID {} не найдена!", compilationId);
            throw new NoSuchElementException("Подборка с таким ID не найдена!");
        }
        return optionalCompilation.get();
    }

    @Override
    public void unpinCompilation(long compilationId) throws NoSuchElementException {
        Compilation compilation = getCompilationById(compilationId);
        compilation.setPinned(false);
        compilationRepository.save(compilation);
    }

    @Override
    public CompilationDto pinCompilation(long compilationId) throws NoSuchElementException {
        Compilation compilation = getCompilationById(compilationId);
        compilation.setPinned(true);
        return CompilationMapper.toCompilationDto(compilationRepository.save(compilation));
    }

    private void isValidCompilation(Compilation compilation) {

    }
}
