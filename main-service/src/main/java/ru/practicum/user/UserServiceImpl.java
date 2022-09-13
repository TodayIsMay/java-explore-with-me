package ru.practicum.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.practicum.State;
import ru.practicum.event.Event;
import ru.practicum.event.EventCreateRequest;
import ru.practicum.event.EventMapper;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.exception.EntityIsAlreadyExistsException;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User createUSer(User user) throws EntityIsAlreadyExistsException {
        isValidUser(user);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(Integer from, Integer size) {
        if (from == null) {
            return userRepository.findUsersWithLimit(size);
        }
        return userRepository.findUsersWithLimitFrom(from, size);
    }

    private void isValidUser(User user) throws EntityIsAlreadyExistsException {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            log.error("Пользователь с email {} уже существует", user.getEmail());
            throw new EntityIsAlreadyExistsException("Пользователь с таким email уже существует!");
        }
    }
}