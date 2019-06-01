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


    @RequestMapping(method=RequestMethod.DELETE, value="/user/history/{id}")
    public void deleteHistory(@PathVariable String id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.getHistory().clear();
            userRepository.save(user);
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/user/history/{id}")
    public List<List<String>> showHistory(@PathVariable String id) {

        Optional<User> optUser = userRepository.findById(id);
        return optUser.map(User::getHistory).orElse(null);

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
