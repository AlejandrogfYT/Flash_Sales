package org.example.flash_sales.DTOs;

import java.time.LocalDate;

public record EventDTO(
        Long userId,
        LocalDate start_date,
        LocalDate end_date,
        Long total_tickets,
        String location,
        String event_name,
        String event_description

) {
}
