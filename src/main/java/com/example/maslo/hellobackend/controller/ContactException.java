package com.example.maslo.hellobackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactException extends Exception {

    public ContactException() {
        super();
    }

    public ContactException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactException(String message) {
        super(message);
    }

}
