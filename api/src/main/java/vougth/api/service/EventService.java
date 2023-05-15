package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vougth.api.domain.Event;
import vougth.api.exception.EventNoContentException;
import vougth.api.exception.EventNotFoundException;
import vougth.api.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public ResponseEntity<Event> save(Event event) {
        eventRepository.save(event);
        return ResponseEntity.status(201).body(event);
    }

    public ResponseEntity<List<Event>> findAll(){
        List<Event> eventList = eventRepository.findAll();
        if (eventList.isEmpty()){
            throw new EventNoContentException();
        }
        return ResponseEntity.status(200).body(eventList);
    }

    public List<Event> findAllEvents(){
        List<Event> eventList = eventRepository.findAll();
        if (eventList.isEmpty()){
            throw new EventNoContentException();
        }
        return eventList;
    }

    public ResponseEntity<Optional<Event>> findById(int id){
        if (eventRepository.findById(id).isEmpty()){
            throw new EventNotFoundException();
        }
        return ResponseEntity.status(200).body(eventRepository.findById(id));
    }

    public ResponseEntity<Void> deleteEventById(int id){
        if (eventRepository.existsById(id)){
            eventRepository.deleteById(id);
        } else {
            throw new EventNotFoundException();
        }
        return ResponseEntity.status(200).build();
    }

    public Event update(int id, Event event){
        if (eventRepository.existsById(id)){
            event.setIdEvent(id);
            eventRepository.save(event);
            return event;
        }
        throw new EventNotFoundException();
    }

    public Long count(){
        return eventRepository.count();
    }
}
