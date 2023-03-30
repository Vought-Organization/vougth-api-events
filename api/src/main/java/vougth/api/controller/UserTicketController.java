package vougth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vougth.api.domain.UserTicket;
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
