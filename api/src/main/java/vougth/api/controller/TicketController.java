package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Ticket;
import vougth.api.repository.TicketRepository;
import vougth.api.service.TicketService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("v1/tickets")
public class TicketController {
    @Autowired private TicketRepository ticketRepository;
    @Autowired private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTicket() {
        return ticketService.getAllTicket();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int id) {
        return ticketService.getTicketById(id);
    }
}

