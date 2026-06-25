package org.example.flash_sales.DTOs;

import org.example.flash_sales.Models.Event;
import org.example.flash_sales.Models.Ticket;
import org.example.flash_sales.Models.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TicketDTO(
        Long id,
        String name,
        BigDecimal price,
        Long stock,
        String userId,
        Long eventId,
        LocalDate date
) {
    public static TicketDTO fromEntity(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        return new TicketDTO(
                ticket.getId(),
                ticket.getName(),
                ticket.getPrice(),
                ticket.getStock(),
                ticket.getUser() != null ? ticket.getUser().getId() : null,
                ticket.getEvent() != null ? ticket.getEvent().getId() : null,
                ticket.getDate()
        );
    }

    public Ticket toEntity(User user, Event event) {
        Ticket ticket = new Ticket();
        ticket.setName(this.name());
        ticket.setPrice(this.price());
        ticket.setStock(this.stock());
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setDate(this.date());
        return ticket;
    }
}
