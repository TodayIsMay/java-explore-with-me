package ru.practicum.compilation;

import org.springframework.web.bind.annotation.*;

@RestController
public class CompilationController {
    private final CompilationService compilationService;

    public CompilationController (CompilationService compilationService) {
        this.compilationService = compilationService;
    }

    @PostMapping("/admin/compilations")
    public CompilationDto createCompilation(@RequestBody NewCompilationDto compilation) {
        return compilationService.createCompilation(compilation);
    }

    @DeleteMapping("/admin/compilations/{compilationId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable long compilationId, @PathVariable long eventId) {
        compilationService.deleteEventFromCompilation(compilationId, eventId);
    }
}
