package ru.practicum.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.Category;
import ru.practicum.user.dto.UserShortDto;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventShortDto {
    private long id;
    private String description;
    private String annotation;
    //private Category category;
    //private int confirmedRequests = 0;
    private LocalDateTime eventDate;
    //private User initiator;
    private String title;
    //private int views = 0;
}
