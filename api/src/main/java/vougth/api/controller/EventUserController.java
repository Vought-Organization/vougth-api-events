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
import vougth.api.util.FilaObjUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user-events")
public class EventUserController {
    @Autowired
    private EventUserRepository eventUserRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/atualiza-eventos/{id_user}")
    public ResponseEntity adicionarEstiloTatuador(@PathVariable Integer id_user, @RequestBody List<Integer> id_events ){
        List<EventUser> events = eventUserRepository.findAll();

        FilaObjUtil<Integer> fila = new FilaObjUtil(id_events.size());
        for(int x = 0 ; x < id_events.size(); x++){
            fila.insert(id_events.get(x));
        }

        for(int x = 0; x < events.size(); x++){
            if(events.get(x).getId_user() != null){
                if(events.get(x).getId_user().equals(id_user)) {
                    deletaEventUser(events.get(x).getId());
                }
            }
        }

        for(int x = 0; x < id_events.size() ; x++){
            EventUser eventUser = new EventUser(id_user, fila.poll());
            eventUserRepository.save(eventUser);
        }
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> deletaEventUser(@PathVariable Integer id) {
        if (eventUserRepository.existsById(id)){
            eventUserRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/")
    public ResponseEntity findAllEventUser(){
        List<EventUser> lista = eventUserRepository.findAll();
        if(lista.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/id-user/{id}")
    public ResponseEntity findEventByIdUser(@PathVariable Integer id){
        List<EventUser> eventUsers = eventUserRepository.findAll();
        List<Integer> idEvents = new ArrayList<>();
        List<Optional<Event>> userEvent = new ArrayList<>();

        for(int x = 0; x < eventUsers.size(); x++){
            if(eventUsers.get(x).getId_user().equals(id)){
                idEvents.add(eventUsers.get(x).getId_event());
            }
        }

        for(Integer idEvent : idEvents){
            Optional<Event> event = eventRepository.findById(idEvent);
            userEvent.add(event);
        }

        if(userEvent.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(userEvent);
    }


    @GetMapping("/id-event/{id}")
    public ResponseEntity findUserbyEvent(@PathVariable Integer id){
        List<EventUser> eventUsers = eventUserRepository.findAll();
        List<Integer> idUser = new ArrayList<>();
        List<Optional<User>> userEvent = new ArrayList<>();

        Integer[] idEvents;
        for(int x = 0; x < eventUsers.size(); x++){
            if(eventUsers.get(x).getId_event().equals(id)){
                idUser.add(eventUsers.get(x).getId_user());
            }
        }

        for(Integer idUsers : idUser){
            Optional<User> user = userRepository.findById(idUsers);
            userEvent.add(user);
        }
        if(userEvent.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(userEvent);
    }
}
