package io.github.untalsanders.contacts.shared.domain;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int status;
    private final String message;
    private String stackTrace;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
