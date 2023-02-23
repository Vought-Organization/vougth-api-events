package vougth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.User;
import vougth.api.response.UserResponseDto;
import vougth.api.repository.UserRepository;
import vougth.api.service.UserService;

import java.util.List;

@RestController @RequestMapping("v1/users") @CrossOrigin
public class UserController {
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;

    @PostMapping @ResponseStatus(HttpStatus.CREATED) @Operation(summary = "Cadastra um usuário")
    public ResponseEntity<User> createUser(@RequestBody User newUser){ return userService.createUser(newUser); }

    @GetMapping("/login") @ResponseStatus(HttpStatus.OK) @Operation(summary = "Executa o Login")
    public ResponseEntity<List<UserResponseDto>> getLogin() { return userService.getLogin(); }

    @GetMapping @ResponseStatus(HttpStatus.OK) @Operation(summary = "Lista todos os usuários")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList = userRepository.findAll();
        return userList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(userList);
    }

    @GetMapping("/{id}") @ResponseStatus(HttpStatus.OK) @Operation(summary = "Busca um usuário específico")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.OK) @Operation(summary = "Deleta um usuário específico")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id){
        return userService.deleteUserById(id);
    }

    @PutMapping("/{id}") @ResponseStatus(HttpStatus.OK) @Operation(summary = "Atualiza um usuário específico")
    public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody User updatedUser){
        return userService.updateUserById(id, updatedUser);
    }
}
