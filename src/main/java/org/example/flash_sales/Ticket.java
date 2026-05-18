package org.example.flash_sales;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Ticket extends SellableItem{
    @ManyToOne
    @JoinColumn(name = "event_id")
    @NotNull
    private Event event;

    @NotNull
    private Date date;


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
