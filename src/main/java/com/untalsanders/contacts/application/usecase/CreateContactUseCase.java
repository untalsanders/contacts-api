package com.untalsanders.contacts.application.usecase;

import com.untalsanders.contacts.domain.model.Contact;

public interface CreateContactUseCase {
    Contact createContact(Contact contact);
}
