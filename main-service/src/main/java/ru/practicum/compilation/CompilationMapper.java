package ru.practicum.compilation;

import ru.practicum.event.EventMapper;

public class CompilationMapper {
    public static CompilationDto toCompilationDto(Compilation compilation) {
        return new CompilationDto(EventMapper.toEventShortDtos(compilation.getEvents()),
                compilation.getId(),
                compilation.getPinned(),
                compilation.getTitle());
    }
}
