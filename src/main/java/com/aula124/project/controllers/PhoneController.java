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
import com.aula124.project.repositories.ContactRepository;
import com.aula124.project.repositories.PhoneRepository;

@RestController
@RequestMapping("/api")
public class PhoneController {

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    ContactRepository contactRepository;

    @PostMapping("/contacts/{id}/phones")
    //localhost:8080/api/contacts/1/phones
    public ResponseEntity<Phone> addPhoneNumber(
        @PathVariable("id") long id,
        @RequestBody Phone phone
    ){

        Contact _contact = contactRepository.findById(id);
        
        phone.setContact(_contact);

        Phone _phone = phoneRepository.save(phone);

        return new ResponseEntity<Phone>(_phone, HttpStatus.CREATED);
    }

    @GetMapping("/contacts/{id}/phones")
    //localhost:8080/api/contacts/1/phones
    public ResponseEntity<List<Phone>> getByContact(@PathVariable("id") long id){

        List<Phone> _phones = phoneRepository.findByContact(id);

        if(_phones.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Phone>>(_phones, HttpStatus.OK);

    }
    
}
