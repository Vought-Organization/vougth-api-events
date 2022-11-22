package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import vougth.api.domain.EventTicket;
import vougth.api.exception.EventNotFound;
import vougth.api.repository.EventRepository;
import vougth.api.repository.EventTicketRepository;
import vougth.api.repository.TicketRepository;
import vougth.api.request.NewBuy;

import javax.validation.Valid;

@RestController
@RequestMapping("event/ticket")
public class EventTicketController {
    @Autowired
    private EventTicketRepository eventTicketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping
    public ResponseEntity<EventTicket> post(
            @RequestBody @Valid
            NewBuy newBuy
    ) {

        if (!ticketRepository.existsById(newBuy.getIdTicket())) {
            // Aqui lançamos uma exceção (erro) do tipo ResponseStatusException
            // o 1o argumento é o status de resposta
            // o 2o argumento é a mensagem de erro
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ticket não encontrado");
        }
        if (!eventRepository.existsById(newBuy.getIdEvent())) {
/*
Como a MotoristaNaoExisteException está anotada com @ResponseStatus,
O StringBoot vai usar o status de resposta e mensagem
configurados nela
 */
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event não encontrado");
        }

        EventTicket newEventTicket = new EventTicket();

        newEventTicket.setTicket(
                ticketRepository.findById(newEventTicket.getTicket().getIdTicket()).get());
        newEventTicket.setEvent(
                eventRepository.findById(newEventTicket.getEvent().getIdEvent()).get());

        newEventTicket.setApproved(newBuy.isApproved());
        eventTicketRepository.save(newEventTicket);

        return ResponseEntity.status(201).body(newEventTicket);
    }
}
