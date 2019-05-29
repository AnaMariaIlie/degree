package com.degree.project.controllers;

import com.degree.project.models.Drug;
import com.degree.project.models.User;
import com.degree.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method=RequestMethod.GET, value="/user/{id}")
    public Optional<User> show(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/user/{id}")
    public User save(@PathVariable String id) {

        User user = new User();
        user.setId(id);
        userRepository.save(user);
        return user;
    }

    private void addInEntry(String string, List<String> entry) {
        if (!"".equals(string)) {
            entry.add(string);
        }
    }
}
