package com.windowsxp.opportunetrewrite.services;

import com.windowsxp.opportunetrewrite.entities.User;
import com.windowsxp.opportunetrewrite.exceptions.custom.UserNotFoundException;
import com.windowsxp.opportunetrewrite.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (!optUser.isPresent()) {
            throw new UserNotFoundException("User with id " + id + " is not found");
        }

        return optUser.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        Optional<User> optUser = userRepository.findById(id);
        if (!optUser.isPresent()) {
            throw new UserNotFoundException("User with id " + id + " is not found");
        }

        user.setId(id);

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (!optUser.isPresent()) {
            throw new UserNotFoundException("User with id " + id + " is not found");
        }

        userRepository.deleteById(id);
    }
}
