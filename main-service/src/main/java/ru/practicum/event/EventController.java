package ru.practicum.event;

import org.springframework.web.bind.annotation.*;
import ru.practicum.event.dto.EventShortDto;

import java.util.List;

@RestController
public class EventController {
    private final EventServiceImpl eventService;

    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<EventShortDto> getEvents() {
        return eventService.getAll();
    }

    @GetMapping("/events/{eventId}")
    public EventShortDto getEventById(@PathVariable long eventId) {
        return eventService.getEventById(eventId);
    }

    @PostMapping("users/{userId}/events")
    public EventShortDto postEvent(@PathVariable long userId, @RequestBody EventCreateRequest eventRequest) {
        return eventService.postEvent(userId, eventRequest);
    }
}
