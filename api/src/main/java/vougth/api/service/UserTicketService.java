package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import vougth.api.domain.UserTicket;
import vougth.api.repository.TicketRepository;
import vougth.api.repository.UserRepository;
import vougth.api.repository.UserTicketRepository;
import vougth.api.request.NewBuyRequestDto;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class UserTicketService {
    @Autowired private UserTicketRepository userTicketRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private TicketRepository ticketRepository;

    public ResponseEntity<UserTicket> post(@RequestBody @Valid NewBuyRequestDto newBuyRequestDto) {
        if (!ticketRepository.existsById(newBuyRequestDto.getIdTicket())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket não encontrado");
        }
        if (!userRepository.existsById(newBuyRequestDto.getIdUser())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event não encontrado");
        }
        UserTicket newUserTicket = new UserTicket();

        newUserTicket.setTicket(ticketRepository.findById(newUserTicket.getTicket().getIdTicket()).get());
        newUserTicket.setUser(userRepository.findById(newUserTicket.getUser().getIdUser()).get());

        newUserTicket.setApproved(newBuyRequestDto.isApproved());
        userTicketRepository.save(newUserTicket);

        return ResponseEntity.status(201).body(newUserTicket);
    }
}
