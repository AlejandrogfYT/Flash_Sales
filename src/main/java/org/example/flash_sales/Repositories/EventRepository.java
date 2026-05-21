package org.example.flash_sales.Repositories;

import org.example.flash_sales.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

interface EventRepository extends JpaRepository<Event, Long> {
}
