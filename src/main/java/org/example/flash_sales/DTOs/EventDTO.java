package org.example.flash_sales.DTOs;

import org.example.flash_sales.Models.Event;
import org.example.flash_sales.Models.User;

import java.time.LocalDate;

public record EventDTO(
        Long id,
        String userId,
        LocalDate start_date,
        LocalDate end_date,
        Long total_tickets,
        String location,
        String event_name,
        String event_description
) {
    public static EventDTO fromEntity(Event event) {
        if (event == null) {
            return null;
        }
        return new EventDTO(
                event.getId(),
                event.getUser() != null ? event.getUser().getId() : null,
                event.getStart_date(),
                event.getEnd_date(),
                event.getTotal_tickets(),
                event.getLocation(),
                event.getEvent_name(),
                event.getEvent_description()
        );
    }

    public Event toEntity(User user) {
        Event event = new Event();
        event.setUser(user);
        event.setStart_date(this.start_date());
        event.setEnd_date(this.end_date());
        event.setTotal_tickets(this.total_tickets());
        event.setLocation(this.location());
        event.setEvent_name(this.event_name());
        event.setEvent_description(this.event_description());
        return event;
    }
}
