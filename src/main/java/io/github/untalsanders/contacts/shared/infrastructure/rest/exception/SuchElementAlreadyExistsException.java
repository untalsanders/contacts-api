package io.github.untalsanders.contacts.shared.infrastructure.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SuchElementAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public SuchElementAlreadyExistsException(String message) {
        super(message);
    }
}
