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
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " is not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
       userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));

       updatedUser.setId(id);

        return userRepository.save(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " is not found"));

        userRepository.deleteById(id);
    }
}
