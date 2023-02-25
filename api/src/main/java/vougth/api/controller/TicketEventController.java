package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Ticket;
import vougth.api.repository.EventRepository;
import vougth.api.service.TicketEventService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("v1/ticket-events")
public class TicketEventController {
    @Autowired
    private TicketEventService ticketEventService;
    @Autowired private EventRepository eventRepository;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket newTicket) {
        return ticketEventService.createTicket(newTicket);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<List<Ticket>> getTickets(@PathVariable Integer id) {
        return ticketEventService.getTickets(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable int id) {
        return ticketEventService.deleteTicketById(id);
    }
}
