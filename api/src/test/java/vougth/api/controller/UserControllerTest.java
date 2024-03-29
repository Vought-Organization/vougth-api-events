package vougth.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import vougth.api.domain.User;
import vougth.api.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserControllerTest {
    @Autowired private UserController controller;
    @MockBean private UserRepository repository;

    @Test @DisplayName("Não retorna lista de users e retorna o status 204")
    void ReturnsWithFailure(){
        when(repository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<User>> listUser = controller.getAllUsers();

        assertEquals(204, listUser.getStatusCodeValue());
        assertNull(listUser.getBody());
    }

    @Test @DisplayName("Retorna lista de users e retorna o status 200")
    void ReturnsWithSucess(){
        when(repository.findAll()).thenReturn(List.of(
                new User(), new User()
        ));
        ResponseEntity<List<User>> listUser = controller.getAllUsers();

        assertEquals(200, listUser.getStatusCodeValue());
        assertTrue(listUser.getBody().size() > 0);
    }
}
