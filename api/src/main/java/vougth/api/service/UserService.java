package vougth.api.service;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vougth.api.domain.User;
import vougth.api.exception.UserNoContentException;
import vougth.api.repository.UserRepository;
import vougth.api.response.UserResponseDto;

import java.util.List;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) { throw new UserNoContentException(); }
        return userList;
    }

    public Long count() { return userRepository.count(); }

    public ResponseEntity<User> createUser(User newUser) {
        userRepository.save(newUser);
        return ResponseEntity.status(201).body(newUser);
    }

    public ResponseEntity<List<UserResponseDto>> getLogin() {
        List<UserResponseDto> userResponseDto = userRepository.getUserResponse();
        if (userResponseDto.isEmpty()){ return ResponseEntity.status(204).build(); }
        return ResponseEntity.status(200).body(userResponseDto);
    }

    public ResponseEntity<User> getUserById(int id){
        return ResponseEntity.of(userRepository.findById(id));
    }

    public ResponseEntity<Void> deleteUserById(int id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<User> updateUserById(int id, User updatedUser){
        if (userRepository.existsById(id)) {
            updatedUser.setIdUser(id);
            userRepository.save(updatedUser);
            return ResponseEntity.status(200).body(updatedUser);
        }
        return ResponseEntity.status(404).build();
    }
}
