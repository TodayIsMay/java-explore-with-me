package ru.practicum.compilation;

public interface CompilationService {

    CompilationDto createCompilation(NewCompilationDto newCompilationDto);

    void deleteEventFromCompilation(long compilationId, long eventId);
}
