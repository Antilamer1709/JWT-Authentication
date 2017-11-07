package com.antilamer.jwtAuthentication.controller;

import com.antilamer.jwtAuthentication.entity.User;
import com.antilamer.jwtAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    public void editUser(@PathVariable long id, @RequestBody User user) {
        User existingUser = userRepository.findOne(id);
        Assert.notNull(existingUser, "User not found");
        existingUser.setName(user.getName());
        userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userRepository.delete(id);
    }
    
}
