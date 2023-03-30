package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vougth.api.domain.Event;
import vougth.api.domain.Ticket;
import vougth.api.exception.EventNotExistsException;
import vougth.api.exception.TicketNotFoundException;
import vougth.api.repository.EventRepository;
import vougth.api.repository.TicketRepository;

import java.util.List;

@Service @RequiredArgsConstructor
public class TicketEventService {
    @Autowired private TicketRepository ticketRepository;
    @Autowired private EventRepository eventRepository;

    public Ticket createTicket(Ticket newTicket) throws EventNotExistsException {
        List<Event> eventList = eventRepository.findAll();
        if (!eventList.isEmpty()) ticketRepository.save(newTicket);
        return newTicket;
    }

    public List<Ticket> getTickets(Integer id) throws TicketNotFoundException {
        return ticketRepository.findByEventCode(id);

    }

    public void deleteTicketById(int id) throws TicketNotFoundException {
        if (ticketRepository.existsById(id)) ticketRepository.deleteById(id);
    }
}
