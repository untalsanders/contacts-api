package io.github.untalsanders.contacts.domain.port.in;

import io.github.untalsanders.contacts.domain.model.Contact;

public interface CreateContactUseCase {
    Contact createContact(Contact contact);
}
