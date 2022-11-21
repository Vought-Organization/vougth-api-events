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
import vougth.api.uteis.FilaObj;
import vougth.api.uteis.PilhaObj;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/events")
@CrossOrigin
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent){
        newEvent.getUser().setOrganize(true);
        eventRepository.save(newEvent);
        return ResponseEntity.status(201).body(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvent(){
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

    // Endpoint que busca os ultimos eventos inseridos no banco pela quantidade solicitada
    @GetMapping("qttd/{qttd}")
    public ResponseEntity findByQttd(@PathVariable Integer qttd){
        List<Event> eventos = eventRepository.findAll();
        List<Event> ultimosEventos = new ArrayList<>();

        if (!eventos.isEmpty()){
            if (eventos.size() <= qttd){
                return ResponseEntity.status(200).body(eventos);
            }

            PilhaObj<Event> ultimosRegistrosPilha = new PilhaObj<>(eventos.size());

            for (Integer i = eventos.size() - 1; i > eventos.size() - qttd - 1; i--){
                ultimosRegistrosPilha.push(eventos.get(i));
            }

            for (Integer i = 0; i < qttd; i++){
                ultimosEventos.add(ultimosRegistrosPilha.pop());
            }

            return ResponseEntity.status(200).body(ultimosEventos);
        }
        return ResponseEntity.status(204).build();
    }
}

