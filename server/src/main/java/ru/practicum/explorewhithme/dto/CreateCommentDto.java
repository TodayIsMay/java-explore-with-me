package ru.practicum.explorewhithme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentDto {
    private String text;
    private Long eventId;
    private Long authorId;
}
