package com.aula124.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aula124.project.models.User;
import com.aula124.project.repositories.UserRepository;

@RestController
@RequestMapping("/api")
//localhost:8080/api/...
public class UserController{
    
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    //localhost:8080/api/users - POST
    public ResponseEntity<User> createUser(@RequestBody User user){

        User _user = userRepository.save(user);

        return new ResponseEntity<User>(_user, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    //localhost:8080/api/users/?username=wagner - GET
    public ResponseEntity<List<User>> findByName(@RequestParam String username){

        List<User> _users = userRepository.findByUsername(username);

        if(_users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<User>>(_users, HttpStatus.OK);

    }

    @GetMapping("users/{id}")
    //localhost:8080/api/users/1 - GET
    public ResponseEntity<User> getById(@PathVariable("id") long id){

        User _user = userRepository.findById(id);

        if (_user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);   
        }

        return new ResponseEntity<User>(_user, HttpStatus.OK);

    }


}
