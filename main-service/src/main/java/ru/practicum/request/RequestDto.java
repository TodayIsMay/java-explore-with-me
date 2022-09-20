package ru.practicum.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Long id;
    private RequestStatus status;
    private LocalDateTime created;
    private Long requester;
    private Long event;
}
