package io.github.untalsanders.contacts.shared.domain;

import lombok.*;

@Getter
public class ErrorResponse {
    private final String code;
    private final String message;
    @Setter
    private String stackTrace;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
