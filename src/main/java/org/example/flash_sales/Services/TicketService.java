package org.example.flash_sales.Services;

import org.example.flash_sales.DTOs.TicketDTO;
import org.example.flash_sales.Models.Event;
import org.example.flash_sales.Models.Ticket;
import org.example.flash_sales.Models.User;
import org.example.flash_sales.Repositories.EventRepository;
import org.example.flash_sales.Repositories.TicketRepository;
import org.example.flash_sales.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public TicketDTO createTicket(TicketDTO dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow();
        Event event = eventRepository.findById(dto.eventId()).orElseThrow();
        Ticket ticket = dto.toEntity(user, event);
        Ticket saved = ticketRepository.save(ticket);
        return TicketDTO.fromEntity(saved);
    }

    public TicketDTO getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        return TicketDTO.fromEntity(ticket);
    }

    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(TicketDTO::fromEntity)
                .toList();
    }

    public TicketDTO updateTicket(Long id, TicketDTO dto) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        User user = userRepository.findById(dto.userId()).orElseThrow();
        Event event = eventRepository.findById(dto.eventId()).orElseThrow();
        ticket.setName(dto.name());
        ticket.setPrice(dto.price());
        ticket.setStock(dto.stock());
        ticket.setUser(user);
        ticket.setEvent(event);
        ticket.setDate(dto.date());
        Ticket saved = ticketRepository.save(ticket);
        return TicketDTO.fromEntity(saved);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
