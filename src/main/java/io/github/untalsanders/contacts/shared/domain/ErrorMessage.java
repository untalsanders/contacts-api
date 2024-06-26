package io.github.untalsanders.contacts.shared.domain;

public enum ErrorMessage {
    UNKNOWN_ERROR("Unknown error occurred"),
    FAILED_TO_FIND_REQUESTED_ELEMENT("Failed to find the requested element"),
    ITEM_ALREADY_EXISTS("The requested element already exists"),;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
