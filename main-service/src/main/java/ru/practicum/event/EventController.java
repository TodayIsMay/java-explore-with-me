package ru.practicum.event;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.event.dto.EventShortDto;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private EventServiceImpl eventService;

    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventShortDto> getEvents() {
        return eventService.getAll();
    }
}
