package ru.practicum.explorewhithme.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.explorewhithme.exception.AccessException;
import ru.practicum.explorewhithme.exception.RequestNotFoundException;
import ru.practicum.explorewhithme.model.Event;
import ru.practicum.explorewhithme.model.Request;
import ru.practicum.explorewhithme.model.Status;
import ru.practicum.explorewhithme.repository.RequestRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestService {
    private final RequestRepository requestRepository;
    private final EventService eventService;
    private final UserService userService;


    public Request save(Long userId, Long eventId) {
        Event event = eventService.findById(eventId);
        if (Objects.equals(event.getInitiatorId(), userId) || !event.getState().equals(Status.PUBLISHED)) { //Условие опубликованности
            throw new IllegalArgumentException("Событие уже опубликовано или запрос оставлен опубликовавшим");
        }
        if (event.getParticipantLimit() != 0) {
            if (event.getParticipantLimit() <= event.getConfirmedRequest()) {
                throw new AccessException("Превышен лимит участников события");
            }
        }
        Request request = new Request();
        request.setCreated(LocalDateTime.now());
        request.setEventId(eventId);
        request.setRequesterId(userId);
        if (!event.getRequestModeration()) {
            request.setStatus(Status.PUBLISHED);
        } else {
            request.setStatus(Status.PENDING);
        }
        return requestRepository.save(request);
    }

    public Request findById(Long id) {
        Optional<Request> r = requestRepository.findById(id);
        if (r.isPresent()) {
            return r.get();
        } else {
            throw new RequestNotFoundException("Запрос с таким ID не найден!");
        }
    }

    public Request cancel(Long userId, Long requestId) {
        Request request = findById(requestId);
        if (!Objects.equals(request.getRequesterId(), userId)) {
            throw new IllegalArgumentException("Отменять запрос может только тот, кто его оставил");
        }
        request.setStatus(Status.CANCELED);
        return request;
    }

    public List<Request> allUserRequests(Long userId) {
        return requestRepository.findByRequesterId(userId);
    }

    public List<Request> getRequestsForEvent(Long userId, Long eventId) {
        Event event = eventService.findById(eventId);
        if (Objects.equals(event.getInitiatorId(), userId)) {
            return requestRepository.findByEventId(eventId);
        } else {
            throw new IllegalArgumentException("Получить список запросов на участие может только владелец события");
        }
    }

    public Request confirmUserRequests(Long userId, Long eventId, Long requestId) {
        Event event = eventService.findById(eventId);
        Request request = findById(requestId);
        if ((event.getParticipantLimit() == 0 || (event.getParticipantLimit() > event.getConfirmedRequest()))
                && Objects.equals(event.getInitiatorId(), userId)) {
            request.setStatus(Status.CONFIRMED);
            event.setConfirmedRequest(event.getConfirmedRequest() + 1);
            requestRepository.save(request);
            if (Objects.equals(event.getConfirmedRequest(), event.getParticipantLimit())
                    || event.getParticipantLimit() != 0) {
                List<Request> requests = requestRepository.findByEventId(eventId);
                for (Request req : requests) {
                    if (req.getStatus().equals(Status.PENDING)) {
                        req.setStatus(Status.REJECTED);
                        requestRepository.save(req);
                    }
                }
            }
            return request;
        }
        throw new AccessException("Превышен лимит участников, либо вы не являетесь владельцем события");
    }

    /**
     *
     * @param userId для проверки существования пользователя
     * @param eventId для проверки существования события
     * @param requestId для поиска запроса
     * @return изменённый запрос
     */
    public Request rejectUserRequests(Long userId, Long eventId, Long requestId) {
        eventService.findById(eventId);
        userService.findById(userId);
        Request request = findById(requestId);
        request.setStatus(Status.REJECTED);
        return request;
    }
}