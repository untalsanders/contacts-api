package io.github.untalsanders.contacts.application.usecase;

import io.github.untalsanders.contacts.domain.model.Contact;

public interface CreateContactUseCase {
    Contact createContact(Contact contact);
}
