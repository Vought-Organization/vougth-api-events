package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vougth.api.domain.Ticket;
import vougth.api.exception.TicketNotFoundException;
import vougth.api.repository.TicketRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    @Autowired private TicketRepository ticketRepository;

    public ResponseEntity<List<Ticket>> getAllTicket() throws TicketNotFoundException {
        List<Ticket> userList = ticketRepository.findAll();
        return userList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(userList);
    }

    public ResponseEntity<Ticket> getTicketById(int id) throws TicketNotFoundException {
        return ResponseEntity.of(ticketRepository.findById(id));
    }
}
