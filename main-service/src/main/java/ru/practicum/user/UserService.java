package ru.practicum.user;

import ru.practicum.event.EventCreateRequest;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.user.model.User;

import java.util.List;

public interface UserService {
    User createUSer(User user);

    List<User> getAllUsers(Integer from, Integer size);
}
