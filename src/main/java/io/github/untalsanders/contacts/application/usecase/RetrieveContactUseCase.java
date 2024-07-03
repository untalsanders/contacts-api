package io.github.untalsanders.contacts.application.usecase;

import io.github.untalsanders.contacts.domain.exception.ContactNotFoundException;
import io.github.untalsanders.contacts.domain.model.Contact;

import java.util.List;
import java.util.Optional;

public interface RetrieveContactUseCase {
    /**
     * Retrieve a <code>Contact</code> by id.
     *
     * @param id the id to search for
     * @return the <code>Contact</code> if found
     */
    Optional<Contact> getContact(Long id) throws ContactNotFoundException;

    /**
     * Retrieve all <code>Contact</code>s.
     *
     * @return <code>Collection</code> of <code>Contact</code>s
     */
    List<Contact> getContacts();
}
