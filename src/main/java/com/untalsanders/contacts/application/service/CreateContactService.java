package com.untalsanders.contacts.application.service;

import com.untalsanders.contacts.application.usecase.CreateContactUseCase;
import com.untalsanders.contacts.domain.model.Contact;
import com.untalsanders.contacts.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateContactService implements CreateContactUseCase {

    private final ContactRepository repository;

    public CreateContactService(ContactRepository contactRepository) {
        this.repository = contactRepository;
    }

    @Override
    public void createContact(Contact contact) {
        repository.save(contact);
    }
}
