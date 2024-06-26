package io.github.untalsanders.contacts.domain.port.in;

import io.github.untalsanders.contacts.domain.model.Contact;

import java.util.Optional;

public interface UpdateContactUseCase {
    Optional<Contact> updateContact(Long id, Contact contact);
}
