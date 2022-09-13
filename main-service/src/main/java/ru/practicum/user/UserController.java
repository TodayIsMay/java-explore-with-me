package ru.practicum.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.exception.EntityIsAlreadyExistsException;
import ru.practicum.user.model.User;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin/users")
    public User createUser(@RequestBody User user) throws EntityIsAlreadyExistsException {
        return userService.createUSer(user);
    }

    @GetMapping("/admin/users")
    public List<User> getAllUsers(@RequestParam(required = false) Integer from,
                                  @RequestParam(required = false, defaultValue = "10") Integer size) {
        return userService.getAllUsers(from, size);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleAlreadyExists(final EntityIsAlreadyExistsException e) {
        return new ResponseEntity<>(
                Map.of("error", e.getMessage()),
                HttpStatus.CONFLICT
        );
    }
}