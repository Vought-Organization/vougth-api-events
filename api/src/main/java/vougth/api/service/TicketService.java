package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vougth.api.domain.Ticket;
import vougth.api.repository.TicketRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public ResponseEntity<Ticket> createTicket(Ticket newTicket) {
        ticketRepository.save(newTicket);
        return ResponseEntity.status(201).body(newTicket);
    }

    public ResponseEntity<List<Ticket>> getAllTicket() {
        List<Ticket> userList = ticketRepository.findAll();
        return userList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(userList);
    }

    public ResponseEntity<Ticket> getTicketById(int id) {
        return ResponseEntity.of(ticketRepository.findById(id));
    }

    public List<Ticket> getTickets(Integer id) {
        return ticketRepository.findByCodigoEvento(id);
    }

    public ResponseEntity<Void> deleteTicketById(int id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

}
