package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAll(){
        List<Event> eventList = eventRepository.findAll();
        if (eventList.isEmpty()){
            throw new EventNoContentException();
        }
        return eventList;
    }

    public Optional<Event> findById(int id){
        if (eventRepository.findById(id).isEmpty()){
            throw new EventNotFoundException();
        }
        return eventRepository.findById(id);
    }

    public void delete(int id){
        if (eventRepository.existsById(id)){
            eventRepository.deleteById(id);
        }else {
            throw new EventNotFoundException();
        }
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
