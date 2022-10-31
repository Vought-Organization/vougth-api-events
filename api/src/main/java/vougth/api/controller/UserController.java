package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.User;
import vougth.api.dto.CredencialDto;
import vougth.api.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        userRepository.save(newUser);
        return ResponseEntity.status(201).body(newUser);
    }

    @GetMapping("/credenciais")
    public ResponseEntity<List<CredencialDto>> getCredenciais(){
        return ResponseEntity.status(200).body(userRepository.getCredenciais());
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList = userRepository.findAll();
        return userList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return ResponseEntity.of(userRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable int id, @RequestBody User updatedUser){
        if (userRepository.existsById(id)){
            updatedUser.setIdUser(id);
            userRepository.save(updatedUser);
            return ResponseEntity.status(200).body(updatedUser);
        }
        return ResponseEntity.status(404).build();
    }
}
