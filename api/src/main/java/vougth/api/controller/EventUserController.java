package vougth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Event;
import vougth.api.domain.EventUser;
import vougth.api.domain.User;
import vougth.api.service.EventUserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user-events")
public class EventUserController {
    @Autowired private EventUserService eventUserService;

    @PostMapping("/update-events/{id_user}") @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adiciona uma relação entre Usuário e Evento")
    public ResponseEntity<Void> addEventUser(@PathVariable Integer id_user, @RequestBody List<Integer> id_events) {
        eventUserService.addEventUser(id_user, id_events);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Deleta uma relação entre Usuário e Evento")
    public ResponseEntity<Void> deleteEventUser(@PathVariable Integer id) {
        eventUserService.deleteEventUser(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todas as relações entre Usuário e Evento")
    public ResponseEntity<List<EventUser>> findAllEventsUser() {
        List<EventUser> list = eventUserService.findAllEventsUser();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/id-user/{id}") @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos os eventos de um usuário específico")
    public ResponseEntity<List<Optional<Event>>> findEventByUser(@PathVariable Integer id) {
        List<Optional<Event>> list = eventUserService.findEventByUser(id);
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/id-event/{id}") @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos os usuários de um evento específico")
    public ResponseEntity<List<Optional<User>>> findUserbyEvent(@PathVariable Integer id) {
        List<Optional<User>> list = eventUserService.findUserbyEvent(id);
        return ResponseEntity.status(200).body(list);
    }
}
