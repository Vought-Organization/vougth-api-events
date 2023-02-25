package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vougth.api.domain.Event;
import vougth.api.domain.EventUser;
import vougth.api.domain.User;
import vougth.api.exception.EventNotFoundException;
import vougth.api.exception.EventUserNotFoundException;
import vougth.api.exception.UserNotFoundException;
import vougth.api.repository.EventRepository;
import vougth.api.repository.EventUserRepository;
import vougth.api.repository.UserRepository;
import vougth.api.util.FilaObjUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventUserService {
    @Autowired private EventUserRepository eventUserRepository;
    @Autowired private EventRepository eventRepository;
    @Autowired private UserRepository userRepository;

    public void addEventUser(Integer id_user, List<Integer> id_events) {
        List<EventUser> events = eventUserRepository.findAll();
        FilaObjUtil<Integer> fila = new FilaObjUtil<Integer>(id_events.size());

        try {
            for (Integer idEvent : id_events) fila.insert(idEvent);

            for (EventUser event : events) {
                if (event.getIdUser() != null && event.getIdUser().equals(id_user))
                    deleteEventUser(event.getId());
            }

            for (Integer idEvent : id_events) {
                EventUser eventUser = new EventUser(id_user, fila.poll());
                eventUserRepository.save(eventUser);
            }
        } catch (EventNotFoundException | EventUserNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void deleteEventUser(Integer id) {
        if (!eventUserRepository.existsById(id)){
            throw new EventUserNotFoundException();
        }
        eventUserRepository.deleteById(id);
    }

    public List<EventUser> findAllEventsUser() {
        List<EventUser> list = eventUserRepository.findAll();
        if (list.isEmpty()) throw new EventUserNotFoundException();
        return list;
    }

    public List<Optional<Event>> findEventByUser(Integer id){
        List<EventUser> eventUsers = eventUserRepository.findAll();
        List<Integer> idEvents = new ArrayList<>();
        List<Optional<Event>> userEvent = new ArrayList<>();

        for (EventUser eventUser : eventUsers) {
            if (eventUser.getIdUser().equals(id)) {
                idEvents.add(eventUser.getIdEvent());
            }
        }

        for (int idEvent : idEvents){
            Optional<Event> event = eventRepository.findById(idEvent);
            userEvent.add(event);
        }

        if (userEvent.isEmpty()) throw new EventUserNotFoundException();
        return userEvent;
    }

    public List<Optional<User>> findUserbyEvent(Integer id){
        List<EventUser> eventUsers = eventUserRepository.findAll();
        List<Integer> idUser = new ArrayList<>();
        List<Optional<User>> userEvent = new ArrayList<>();

        for (EventUser eventUser : eventUsers) {
            if (eventUser.getIdEvent().equals(id)) {
                idUser.add(eventUser.getIdUser());
            }
        }

        for (Integer idUsers : idUser){
            Optional<User> user = userRepository.findById(idUsers);
            userEvent.add(user);
        }

        if (userEvent.isEmpty()) throw new UserNotFoundException();
        return userEvent;
    }
}
