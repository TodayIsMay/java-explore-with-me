package ru.practicum.explorewhithme.controller;

import io.swagger.v3.oas.annotations.headers.Header;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explorewhithme.dto.UserDto;
import ru.practicum.explorewhithme.mapper.UserMapper;
import ru.practicum.explorewhithme.model.User;
import ru.practicum.explorewhithme.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = { "http://localhost:8080", "https://procrastinate-olz3.vercel.app/" })
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/version")
    public String getVersion() {
        String version = "31.10.2022 17:48";
        log.info(version);
        return version;
    }

    @GetMapping
    public List<UserDto> getAll() {
        List<User> allUsers = new ArrayList<>(userService.findAll());
        log.info("Пользователей в базе: {}", allUsers.size());
        return userMapper.toUserDtoList(allUsers);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        log.info("Запрошен пользователь id: " + id);
        User user = userService.findById(id);
        return userMapper.toUserDto(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User savedUser = userService.save(user);
        log.info("Новый пользователь: " + savedUser);
        UserDto userDto1 = userMapper.toUserDto(savedUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "localhost:8080");
        return new ResponseEntity<>(userDto1, headers, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable long id, @Valid @RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User userForUpdate = userService.upDate(user, id);
        log.info("Update user: " + user);
        return userMapper.toUserDto(userForUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }
}