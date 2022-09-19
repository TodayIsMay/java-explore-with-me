package ru.practicum.compilation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
    @Query(nativeQuery = true, value = "DELETE FROM compilation_events WHERE compilation_id = ? AND event_id = ?")
    void deleteEventFromCompilation(long compilationId, long eventId);

    @Query(nativeQuery = true, value = "INSERT INTO compilation_events (event_id, compilation_id) VALUES (?, ?)")
    void addEventsToCompilation(long eventId, long compilationId);
}
