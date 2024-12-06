package io.github.untalsanders.contacts.shared.infrastructure.rest.exception;

public class ResponseNotFoundException extends RuntimeException {
    public ResponseNotFoundException(String message) {
        super(message);
    }
}
