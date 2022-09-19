package ru.practicum.compilation;

import org.springframework.stereotype.Service;
import ru.practicum.event.Event;
import ru.practicum.event.repository.EventRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;

    public CompilationServiceImpl(CompilationRepository compilationRepository, EventRepository eventRepository) {
        this.compilationRepository = compilationRepository;
        this.eventRepository = eventRepository;
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

    private void isValidCompilation(Compilation compilation) {

    }
}
