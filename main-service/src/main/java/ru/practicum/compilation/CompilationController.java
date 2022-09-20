package ru.practicum.compilation;

import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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

    @PatchMapping("/admin/compilations/{compilationId}/events/{eventId}")
    public CompilationDto addEventToCompilation(@PathVariable long compilationId, @PathVariable long eventId)
            throws NoSuchElementException {
        return compilationService.addEventToCompilation(compilationId, eventId);
    }

    @DeleteMapping("admin/compilations/{compilationId}/pin")
    public void unpinCompilation(@PathVariable long compilationId) {
        compilationService.unpinCompilation(compilationId);
    }

    @PatchMapping("/admin/compilations/{compilationId}/pin")
    public CompilationDto pinCompilation(@PathVariable long compilationId) {
        return compilationService.pinCompilation(compilationId);
    }
}
