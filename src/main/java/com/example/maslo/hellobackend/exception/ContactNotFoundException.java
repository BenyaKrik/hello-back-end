package com.example.maslo.hellobackend.exception;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException() {
        super();
    }

    public ContactNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ContactNotFoundException(final String message) {
        super(message);
    }

    public ContactNotFoundException(final Throwable cause) {
        super(cause);
    }



}

