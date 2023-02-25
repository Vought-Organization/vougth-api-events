package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Event;
import vougth.api.domain.EventUser;
import vougth.api.domain.User;
import vougth.api.repository.EventRepository;
import vougth.api.repository.EventUserRepository;
import vougth.api.repository.UserRepository;
import vougth.api.service.EventUserService;
import vougth.api.util.FilaObjUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user-events")
public class EventUserController {
    @Autowired private EventUserService eventUserService;

    @PostMapping("/update-events/{id_user}")
    public ResponseEntity<Void> addEventUser(@PathVariable Integer id_user, @RequestBody List<Integer> id_events ) {
        return eventUserService.addEventUser(id_user, id_events);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventUser(@PathVariable Integer id) {
        return eventUserService.deleteEventUser(id);
    }

    @GetMapping
    public ResponseEntity<List<EventUser>> findAllEventsUser() {
        return eventUserService.findAllEventsUser();
    }

    @GetMapping("/id-user/{id}")
    public ResponseEntity<List<Optional<Event>>> findEventByUser(@PathVariable Integer id) {
        return eventUserService.findEventByUser(id);
    }

    @GetMapping("/id-event/{id}")
    public ResponseEntity<List<Optional<User>>> findUserbyEvent(@PathVariable Integer id) {
        return eventUserService.findUserbyEvent(id);
    }
}
