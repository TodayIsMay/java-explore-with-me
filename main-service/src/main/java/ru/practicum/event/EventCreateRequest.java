package ru.practicum.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCreateRequest {
    private String title;
    private String description;
    private String annotation;
    private LocalDateTime eventDate;
    private Boolean paid;
    private Long category;
    private Integer participantLimit;
    private Boolean requestModeration;
}
