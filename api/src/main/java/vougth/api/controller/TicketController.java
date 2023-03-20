package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Ticket;
import vougth.api.service.TicketService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("v1/tickets")
public class TicketController {
    @Autowired private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTicket() {
        List<Ticket> ticketList = ticketService.getAllTicket();
        return (ticketList.isEmpty())
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(ticketList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ticket>> getTicketById(@PathVariable int id) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        return (ticket.isEmpty())
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(ticket);
    }
}
