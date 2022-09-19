package ru.practicum.compilation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.event.Event;
import ru.practicum.event.dto.EventShortDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compilations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compilation_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "pinned")
    private Boolean pinned;

    @ManyToMany
    @JoinTable(name = "compilation_event",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events = new ArrayList<>();
}