package com.example.maslo.hellobackend.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.maslo.hellobackend.controller.ContactController;
import com.example.maslo.hellobackend.model.Contact;
import com.example.maslo.hellobackend.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.LinkedList;
import java.util.List;

@Service
public class ContactService {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactRepository contactRepository;

    @SessionScope
    public List<Contact> findAll(String nameFilter) {
        //List<Contact> allContacts = contactRepository.findAll();
        List<Contact> filteredContacts = new LinkedList<>();
        for (Contact contact : contactRepository.findAll()) {
            if (!contact.getName().matches(nameFilter)) {
                filteredContacts.add(contact);
            }
        }
        return filteredContacts;
    }
}