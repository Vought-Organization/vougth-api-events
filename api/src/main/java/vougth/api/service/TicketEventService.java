package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vougth.api.domain.Event;
import vougth.api.domain.Ticket;
import vougth.api.exception.EventNotExistsException;
import vougth.api.exception.TicketNotFoundException;
import vougth.api.repository.EventRepository;
import vougth.api.repository.TicketRepository;

import java.io.IOException;
import java.util.List;

@Service @RequiredArgsConstructor
public class TicketEventService {
    @Autowired private TicketService ticketService;
    @Autowired private TicketRepository ticketRepository;
    @Autowired private EventRepository eventRepository;

    public ResponseEntity<Ticket> createTicket(Ticket newTicket) throws EventNotExistsException {
        List<Event> eventList = eventRepository.findAll();
        if (!eventList.isEmpty()){
            ticketRepository.save(newTicket);
            return ResponseEntity.status(201).body(newTicket);
        }
        return ResponseEntity.status(500).build();
    }

    public ResponseEntity<List<Ticket>> getTickets(Integer id) throws TicketNotFoundException {
        List<Ticket> ticketList = ticketRepository.findByEventCode(id);
        return (!ticketList.isEmpty())
                ? ResponseEntity.status(200).body(ticketList)
                : ResponseEntity.status(204).build();
    }

    public ResponseEntity<Void> deleteTicketById(int id) throws TicketNotFoundException {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
