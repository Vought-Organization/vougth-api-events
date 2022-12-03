package vougth.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import vougth.api.domain.Event;
import vougth.api.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class EventControllerTest {

    @Autowired
    private EventController controller;

    @MockBean
    private EventRepository repository;

    @Test
    @DisplayName("Não retorna lista de users e retorna o status 204")
    void ReturnsWithFailure(){

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Event>> listEvent = controller.getAllEvent();

        assertEquals(204, listEvent.getStatusCodeValue());
        assertNull(listEvent.getBody());
    }

    @Test
    @DisplayName("Retorna lista de users e retorna o status 200")
    void ReturnsWithSucess(){

        when(repository.findAll()).thenReturn(List.of(
                new Event(),
                new Event()
        ));

        ResponseEntity<List<Event>> listEvent = controller.getAllEvent();

        assertEquals(200, listEvent.getStatusCodeValue());
        assertTrue(listEvent.getBody().size() > 0);
    }

}