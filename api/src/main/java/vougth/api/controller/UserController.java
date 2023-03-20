package vougth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.User;
import vougth.api.response.UserResponseDto;
import vougth.api.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController @RequestMapping("v1/users") @CrossOrigin
public class UserController {
    @Autowired private UserService userService;

    @PostMapping @Operation(summary = "Cadastra um usuário")
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        userService.createUser(newUser);
        return ResponseEntity.status(201).body(newUser);
    }

    @GetMapping("/login") @Operation(summary = "Executa o Login")
    public ResponseEntity<List<UserResponseDto>> getLogin() {
        List<UserResponseDto> loginList = userService.getLogin();
        return ResponseEntity.status(200).body(loginList);
    }

    @GetMapping @Operation(summary = "Lista todos os usuários")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> usersList = userService.getAllUsers();
        return ResponseEntity.status(200).body(usersList);
    }

    @GetMapping("/{id}") @Operation(summary = "Busca um usuário específico")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int id){
        Optional<User> user = userService.getUserById(id);
        return ResponseEntity.status(200).body(user);
    }

    @DeleteMapping("/{id}") @Operation(summary = "Deleta um usuário específico")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}") @Operation(summary = "Atualiza um usuário específico")
    public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody User updatedUser){
        User user = userService.updateUserById(id, updatedUser);
        return ResponseEntity.status(200).body(user);
    }
}
