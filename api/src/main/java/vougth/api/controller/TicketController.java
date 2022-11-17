package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Ticket;
import vougth.api.domain.User;
import vougth.api.repository.TicketRepository;
import vougth.api.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("v1/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket newTicket){
        ticketRepository.save(newTicket);
        return ResponseEntity.status(201).body(newTicket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTicket(){
        List<Ticket> userList = ticketRepository.findAll();
        return userList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int id){
        return ResponseEntity.of(ticketRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable int id){
        if (ticketRepository.existsById(id)){
            ticketRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

}

