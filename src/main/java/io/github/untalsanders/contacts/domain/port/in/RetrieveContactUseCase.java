package io.github.untalsanders.contacts.domain.port.in;

import io.github.untalsanders.contacts.domain.model.Contact;

import java.util.List;
import java.util.Optional;

public interface RetrieveContactUseCase {
    Optional<Contact> getContact(Long id);
    List<Contact> getContacts();
}
