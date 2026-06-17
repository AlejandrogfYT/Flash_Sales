package org.example.flash_sales.Services;

import org.example.flash_sales.DTOs.EventDTO;
import org.example.flash_sales.Models.Event;
import org.example.flash_sales.Models.User;
import org.example.flash_sales.Repositories.EventRepository;
import org.example.flash_sales.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public EventDTO createEvent(EventDTO dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow();
        Event event = dto.toEntity(user);
        Event saved = eventRepository.save(event);
        return EventDTO.fromEntity(saved);
    }

    public EventDTO getEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        return EventDTO.fromEntity(event);
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EventDTO::fromEntity)
                .toList();
    }

    public EventDTO updateEvent(Long id, EventDTO dto) {
        Event event = eventRepository.findById(id).orElseThrow();
        User user = userRepository.findById(dto.userId()).orElseThrow();
        event.setUser(user);
        event.setStart_date(dto.start_date());
        event.setEnd_date(dto.end_date());
        event.setTotal_tickets(dto.total_tickets());
        event.setLocation(dto.location());
        event.setEvent_name(dto.event_name());
        event.setEvent_description(dto.event_description());
        Event saved = eventRepository.save(event);
        return EventDTO.fromEntity(saved);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
