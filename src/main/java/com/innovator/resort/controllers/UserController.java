package com.innovator.resort.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovator.resort.exception.ResourceNotFoundException;
import com.innovator.resort.models.User;
import com.innovator.resort.repositories.UserRepository;

import ch.qos.logback.classic.Logger;



/**
 * Controller class
 * http://localhost:9092/resort
 */
@RestController
@RequestMapping("/resort")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/saveUser")
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @PutMapping("/updateUsers/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setF_name(userDetails.getF_name());
        user.setL_name(userDetails.getL_name());
        user.setSex(userDetails.getSex());
        user.setAge(userDetails.getAge());
        user.setCity(userDetails.getCity());
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}


/*
for update
http://localhost:9090/resort/updateUsers/1
{
  "f_name": "Prasahnt",
  "l_name": "Sagar",
  "age": 30,
  "sex": "male",
  "city": "bangalore",
  "userName": "sagar"
}
 */