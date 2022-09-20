package ru.practicum.request;

import org.springframework.stereotype.Service;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.user.UserRepository;

import java.time.LocalDateTime;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository,
                              EventRepository eventRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public RequestDto createRequest(long eventId, long userId) {
        Request newRequest = new Request();
        newRequest.setStatus(RequestStatus.PENDING);
        newRequest.setCreated(LocalDateTime.now());
        newRequest.setRequester(userRepository.findById(userId).get());
        newRequest.setEvent(eventRepository.findById(eventId).get());
        return RequestMapper.toRequestDto(requestRepository.save(newRequest));
    }
}
