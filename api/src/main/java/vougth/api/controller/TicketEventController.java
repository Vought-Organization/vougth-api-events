package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Ticket;
import vougth.api.service.TicketEventService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("v1/ticket-events")
public class TicketEventController {
    @Autowired
    private TicketEventService ticketEventService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket newTicket) {
        ticketEventService.createTicket(newTicket);
        return ResponseEntity.status(200).body(newTicket);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<List<Ticket>> getTickets(@PathVariable Integer id) {
        List<Ticket> ticketList = ticketEventService.getTickets(id);
        return (!ticketList.isEmpty())
                ? ResponseEntity.status(200).body(ticketList)
                : ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable int id) {
        ticketEventService.deleteTicketById(id);
        return ResponseEntity.status(200).build();
    }
}
