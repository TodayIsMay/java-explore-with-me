package ru.practicum.compilation;

import java.util.NoSuchElementException;

public interface CompilationService {

    CompilationDto createCompilation(NewCompilationDto newCompilationDto);

    void deleteEventFromCompilation(long compilationId, long eventId);

    CompilationDto addEventToCompilation(long compilationId, long eventId) throws NoSuchElementException;

    Compilation getCompilationById(long compilationId) throws NoSuchElementException;

    void unpinCompilation(long compilationId) throws NoSuchElementException;

    CompilationDto pinCompilation(long compilationId) throws NoSuchElementException;
}
