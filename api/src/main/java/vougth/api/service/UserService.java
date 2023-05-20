package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

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
        System.out.println("entrei na service");
//        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
//        System.out.println("antes de salvar a senha codificada");
//        newUser.setPassword(encodedPassword);
//        System.out.println("depois de salvar a senha codificada");
        User returned = userRepository.save(newUser);
        System.out.println("consegui cadastrar");
        return returned;
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
