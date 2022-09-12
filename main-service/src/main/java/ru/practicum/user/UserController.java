package ru.practicum.user;

import org.springframework.web.bind.annotation.*;
import ru.practicum.event.Event;
import ru.practicum.event.dto.EventShortDto;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{userId}/events")
    public EventShortDto postEvent(@PathVariable long userId, @RequestBody Event event) {
        return userService.postEvent(userId, event);
    }
}
