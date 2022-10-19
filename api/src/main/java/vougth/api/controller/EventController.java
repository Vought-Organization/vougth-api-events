package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Event;
import vougth.api.domain.User;
import vougth.api.repository.EventRepository;
import vougth.api.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("v1/events")
@CrossOrigin
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent){
        eventRepository.save(newEvent);
        return ResponseEntity.status(201).body(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllUser(){
        List<Event> eventList = eventRepository.findAll();
        return eventList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(eventList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id){
        return ResponseEntity.of(eventRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable int id){
        if (eventRepository.existsById(id)){
            eventRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateUserById(@PathVariable int id, @RequestBody Event updatedEvent){
        if (eventRepository.existsById(id)){
            updatedEvent.setIdEvent(id);
            eventRepository.save(updatedEvent);
            return ResponseEntity.status(200).body(updatedEvent);
        }
        return ResponseEntity.status(404).build();
    }
}

