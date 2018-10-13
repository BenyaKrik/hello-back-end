package com.example.maslo.hellobackend.service;

import com.example.maslo.hellobackend.exception.ContactNotFoundException;
import com.example.maslo.hellobackend.model.Contact;
import com.example.maslo.hellobackend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    @ExceptionHandler(value = {ContactNotFoundException.class })
    public List<Contact> findByNamePattern(String nameFilter) {
        LinkedList<Contact> filteredContacts = new LinkedList<>();
            Pattern p = Pattern.compile(nameFilter);
            for (Contact contact : contactRepository.findAll()) {
                if (!p.matcher(contact.getName()).matches()) {
                    filteredContacts.add(contact);
                }
            }

        return filteredContacts;
    }
}