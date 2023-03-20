package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vougth.api.domain.User;
import vougth.api.exception.UserNoContentException;
import vougth.api.exception.UserNotFoundException;
import vougth.api.repository.UserRepository;
import vougth.api.response.UserResponseDto;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new UserNoContentException();
        }
        return userList;
    }

    public Long count() {
        return userRepository.count();
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
//        return newUser;
    }

    public List<User> getAllUsers() throws UserNotFoundException {
        return userRepository.findAll();
    }

    public List<UserResponseDto> getLogin() {
        return userRepository.getUserResponse();
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public void deleteUserById(int id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public User updateUserById(int id, User updatedUser){
        if (userRepository.existsById(id)) {
            updatedUser.setIdUser(id);
            userRepository.save(updatedUser);
        }
        return updatedUser;
    }
}
