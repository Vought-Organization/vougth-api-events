package vougth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.UserTicket;
import vougth.api.repository.TicketRepository;
import vougth.api.repository.UserRepository;
import vougth.api.repository.UserTicketRepository;
import vougth.api.request.NewBuyRequestDto;
import vougth.api.service.UserTicketService;

import javax.validation.Valid;

@RestController
@RequestMapping("user/ticket")
public class UserTicketController {
    private UserTicketService service;

    @PostMapping @Operation(summary = "Validação da compra do ingresso")
    public ResponseEntity<UserTicket> post(@RequestBody @Valid NewBuyRequestDto newBuyRequestDto) {
        UserTicket newTicket = service.post(newBuyRequestDto);
        return ResponseEntity.status(201).body(newTicket);
    }
}
