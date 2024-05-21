package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.User;
import com.example.hotelmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}