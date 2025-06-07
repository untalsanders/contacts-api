package com.untalsanders.contacts.application.usecase;

import com.untalsanders.contacts.domain.exception.ContactNotFoundException;
import com.untalsanders.contacts.domain.model.Contact;

import java.util.Optional;

public interface UpdateContactUseCase {
    Optional<Contact> updateContact(Long id, Contact contact) throws ContactNotFoundException;
}
