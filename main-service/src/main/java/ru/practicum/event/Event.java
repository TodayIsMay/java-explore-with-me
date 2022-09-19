package ru.practicum.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.State;
import ru.practicum.category.entitty.Category;
import ru.practicum.user.model.User;
import ru.practicum.util.LocalDateTimeDeserializator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "annotation")
    private String annotation;

    @JsonDeserialize(using = LocalDateTimeDeserializator.class)
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @JsonDeserialize(using = LocalDateTimeDeserializator.class)
    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @JsonDeserialize(using = LocalDateTimeDeserializator.class)
    @Column(name = "published_on")
    private LocalDateTime publishedOn = null;

    @Column(name = "paid")
    private Boolean paid;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "request_moderation")
    private Boolean requestModeration = true;

    @Column(name = "participant_limit")
    private Integer participantLimit = 0;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "initiator")
    private User initiator;
}