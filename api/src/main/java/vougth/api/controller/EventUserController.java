package vougth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Event;
import vougth.api.domain.EventUser;
import vougth.api.domain.User;
import vougth.api.service.EventUserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user-events")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
public class EventUserController {
    @Autowired private EventUserService eventUserService;

    @PostMapping("/update-events/{idUser}")
    @Operation(summary = "Adiciona uma relação entre Usuário e Evento")
    public ResponseEntity<Void> addEventUser(@PathVariable Integer idUser, @RequestBody List<Integer> idEvents) {
        eventUserService.addEventUser(idUser, idEvents);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma relação entre Usuário e Evento")
    public ResponseEntity<Void> deleteEventUser(@PathVariable Integer id) {
        eventUserService.deleteEventUser(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    @Operation(summary = "Busca todas as relações entre Usuário e Evento")
    public ResponseEntity<List<EventUser>> findAllEventsUser() {
        List<EventUser> eventList = eventUserService.findAllEventsUser();
        return (eventList.isEmpty())
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(eventList);
    }

    @GetMapping("/id-user/{id}")
    @Operation(summary = "Busca todos os eventos de um usuário específico")
    public ResponseEntity<List<Optional<Event>>> findEventByUser(@PathVariable Integer id) {
        List<Optional<Event>> eventList = eventUserService.findEventByUser(id);
        return (eventList.isEmpty())
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(eventList);
    }

    @GetMapping("/id-event/{id}")
    @Operation(summary = "Busca todos os usuários de um evento específico")
    public ResponseEntity<List<Optional<User>>> findUserbyEvent(@PathVariable Integer id) {
        List<Optional<User>> eventList = eventUserService.findUserbyEvent(id);
        return (eventList.isEmpty())
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(eventList);
    }
}
