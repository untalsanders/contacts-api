package io.github.untalsanders.contacts.infrastructure.rest.dto;

import lombok.Data;

@Data
public class CreateContactDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
