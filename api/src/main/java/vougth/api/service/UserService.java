package vougth.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vougth.api.domain.User;
import vougth.api.exception.EventNoContentException;
import vougth.api.exception.UserNoContentException;
import vougth.api.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
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
}
