package vougth.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import vougth.api.domain.Ticket;
import vougth.api.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TicketControllerTest {
    @Autowired
    private TicketController controller;

    @MockBean
    private TicketRepository repository;

    @Test
    @DisplayName("Não retorna lista de tickets e retorna o status 204")
    void ReturnsWithFailure(){

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Ticket>> listTicket = controller.getAllTicket();

        assertEquals(204, listTicket.getStatusCodeValue());
        assertNull(listTicket.getBody());
    }

    @Test
    @DisplayName("Retorna lista de tickets e retorna o status 200")
    void ReturnsWithSucess(){

        when(repository.findAll()).thenReturn(List.of(
                new Ticket(),
                new Ticket()
        ));

        ResponseEntity<List<Ticket>> listTicket = controller.getAllTicket();

        assertEquals(200, listTicket.getStatusCodeValue());
        assertTrue(listTicket.getBody().size() > 0);
    }
}