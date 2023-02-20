package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import vougth.api.domain.UserTicket;
import vougth.api.repository.TicketRepository;
import vougth.api.repository.UserRepository;
import vougth.api.repository.UserTicketRepository;
import vougth.api.request.NewBuyRequestDto;

import javax.validation.Valid;

@RestController
@RequestMapping("user/ticket")
public class UserTicketController {
    @Autowired
    private UserTicketRepository userTicketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping
    public ResponseEntity<UserTicket> post(
            @RequestBody @Valid
            NewBuyRequestDto newBuyRequestDto
    ) {

        if (!ticketRepository.existsById(newBuyRequestDto.getIdTicket())) {
            // Aqui lançamos uma exceção (erro) do tipo ResponseStatusException
            // o 1o argumento é o status de resposta
            // o 2o argumento é a mensagem de erro
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ticket não encontrado");
        }
        if (!userRepository.existsById(newBuyRequestDto.getIdUser())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event não encontrado");
        }

        UserTicket newUserTicket = new UserTicket();

        newUserTicket.setTicket(
                ticketRepository.findById(newUserTicket.getTicket().getIdTicket()).get());
        newUserTicket.setUser(
                userRepository.findById(newUserTicket.getUser().getIdUser()).get());

        newUserTicket.setApproved(newBuyRequestDto.isApproved());
        userTicketRepository.save(newUserTicket);

        return ResponseEntity.status(201).body(newUserTicket);
    }
}
