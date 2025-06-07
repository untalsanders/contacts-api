package com.untalsanders.contacts.domain.exception;

/**
 * A class that extends <code>RuntimeException</code> to customize the error
 * message when a <code>Contact</code> not is found.
 *
 * @author Sanders Gutiérrez
 */
public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
