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
import org.springframework.web.bind.annotation.RestController;

import com.aula124.project.models.Contact;
import com.aula124.project.models.Phone;
import com.aula124.project.models.User;
import com.aula124.project.repositories.ContactRepository;
import com.aula124.project.repositories.PhoneRepository;
import com.aula124.project.repositories.UserRepository;

@RestController
@RequestMapping("/api")
//localhost:8080/api/...
public class ContactController{
    
    @Autowired
    ContactRepository contactRepository;
    
    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users/{id}/contacts")
    //localhost:8080/api/users/1/contacts
    public ResponseEntity<Contact> createContact(
        @PathVariable("id") long id,
        @RequestBody Contact contact
    ){
        User _user = userRepository.findById(id);

        //Atrelando um usuário a um contato
        contact.setUser(_user);

        //Salvando um novo contato
        Contact _contact = contactRepository.save(contact);

        return new ResponseEntity<Contact>(_contact, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}/contacts")
    //localhost:8080/api/users/1/contacts
    public ResponseEntity<List<Contact>> getByUser(@PathVariable("id") long id){

        List<Contact> _contacts = contactRepository.findByUser(id);

        if(_contacts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Contact>>(_contacts, HttpStatus.OK);

    }

}
