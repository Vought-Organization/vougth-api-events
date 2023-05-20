package vougth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Event;
import vougth.api.service.EventService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/events")
@CrossOrigin(origins = "*")
public class EventController {
    private final EventService eventService;

    @PostMapping
    @Operation(summary = "Cria Evento")
    public ResponseEntity<Event> save(@Valid @RequestBody Event newEvent) {
        return eventService.save(newEvent);
    }

    @GetMapping
    @Operation(summary = "Busca todos os eventos")
    public ResponseEntity<List<Event>> getAllEvent() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca evento pelo id")
    public ResponseEntity<Optional<Event>> getEventById(@PathVariable int id) {
        return eventService.findById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta evento pelo id")
    public ResponseEntity<Void> deleteEventById(@PathVariable int id) {
        return eventService.deleteEventById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza evento pelo id e passando novo objeto Evento")
    public Event updateUserById(@PathVariable int id, @RequestBody Event updatedEvent) {
        return eventService.update(id, updatedEvent);
    }
}

