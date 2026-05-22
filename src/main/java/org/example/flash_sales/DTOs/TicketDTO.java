package org.example.flash_sales.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TicketDTO(
        String name,
        BigDecimal price,
        Long stock,
        Long userId,
        Long eventId,
        LocalDate date
) {

}
