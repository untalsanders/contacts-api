package io.github.untalsanders.contacts.shared.domain;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    UNKNOWN("Unknown error occurred"),
    FAILED_TO_FIND_REQUESTED_ELEMENT("Failed to find the requested element"),
    ITEM_ALREADY_EXISTS("The requested element already exists");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
