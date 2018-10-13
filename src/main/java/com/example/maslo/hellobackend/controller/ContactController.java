package com.example.maslo.hellobackend.controller;

import com.example.maslo.hellobackend.model.Contact;
import com.example.maslo.hellobackend.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.PatternSyntaxException;

@RestController
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/hello/contacts", params = {"nameFilter"})
    public ResponseEntity<List<Contact>> findContacts(@RequestParam String nameFilter) {
        logger.info("FindContacts start :" + nameFilter);

        if (nameFilter.trim().isEmpty()) {
            throw new PatternSyntaxException("Empty param", null, 0);
        }

        List<Contact> result = contactService.findByNamePattern(nameFilter);

        logger.info("findContacts size:" + result.size());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
