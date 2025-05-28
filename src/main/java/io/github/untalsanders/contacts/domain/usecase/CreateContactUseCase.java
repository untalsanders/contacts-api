package io.github.untalsanders.contacts.domain.usecase;

import io.github.untalsanders.contacts.domain.model.Contact;

public interface CreateContactUseCase {
    Contact createContact(Contact contact);
    Contact createContact(Long id, String firstName, String phoneNumber);
    Contact createContact(Long id, String firstName, String lastName, String phoneNumber);
}
