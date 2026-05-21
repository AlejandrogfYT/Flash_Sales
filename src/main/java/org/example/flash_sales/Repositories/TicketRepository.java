package org.example.flash_sales.Repositories;

import org.example.flash_sales.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

interface TicketRepository extends JpaRepository<Ticket, Long> {
}
