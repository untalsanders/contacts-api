package io.github.untalsanders.contacts.domain.usecase;

import io.github.untalsanders.contacts.domain.exception.ContactNotFoundException;
import io.github.untalsanders.contacts.domain.model.Contact;

import java.util.Optional;

public interface UpdateContactUseCase {
    Optional<Contact> updateContact(Long id, Contact contact) throws ContactNotFoundException;
}
