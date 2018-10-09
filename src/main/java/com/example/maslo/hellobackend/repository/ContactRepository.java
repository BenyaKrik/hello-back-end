package com.example.maslo.hellobackend.repository;

import com.example.maslo.hellobackend.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Long> {
}
