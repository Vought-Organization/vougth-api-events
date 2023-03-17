package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vougth.api.domain.Ticket;
import vougth.api.exception.TicketNotFoundException;
import vougth.api.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    @Autowired private TicketRepository ticketRepository;

    public List<Ticket> getAllTicket() throws TicketNotFoundException {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(int id) throws TicketNotFoundException {
        return ticketRepository.findById(id);
    }
}
