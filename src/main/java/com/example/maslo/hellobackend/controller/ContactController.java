package com.example.maslo.hellobackend.controller;

import com.example.maslo.hellobackend.model.Contact;
import com.example.maslo.hellobackend.service.ContactService;
import com.example.maslo.hellobackend.validator.RegexPatternValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/contacts")
    public ResponseEntity<List<Contact>> findContacts(@RequestParam(value = "nameFilter") String nameFilter) {
        if (new RegexPatternValidator().isValid(nameFilter) != null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(contactService.findAll(nameFilter));

        }
    }

}
