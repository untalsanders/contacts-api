package io.github.untalsanders.contacts.shared.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorResponse {
    private final int status;
    private final String message;
    @Setter
    private String stackTrace;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
