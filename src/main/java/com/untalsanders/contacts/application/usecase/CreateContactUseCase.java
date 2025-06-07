package com.untalsanders.contacts.application.usecase;

import com.untalsanders.contacts.domain.model.Contact;

public interface CreateContactUseCase {
    void createContact(Contact contact);
}
