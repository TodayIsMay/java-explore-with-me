package ru.practicum.request;

import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/users/{userId}/requests")
    public RequestDto createRequest(@PathVariable long userId, @RequestParam long eventId) {
        return requestService.createRequest(eventId, userId);
    }
}
