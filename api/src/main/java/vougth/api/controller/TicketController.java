package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Ticket;
import vougth.api.domain.User;
import vougth.api.repository.TicketRepository;
import vougth.api.repository.UserRepository;
import vougth.api.service.TicketService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("v1/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;


    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket newTicket) {
        return ticketService.createTicket(newTicket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTicket() {
        return ticketService.getAllTicket();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/eventos/{id}")
    public List<Ticket> getTickets(@PathVariable Integer id) {
        return ticketService.getTickets(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable int id) {

        return ticketService.deleteTicketById(id);
    }

}

