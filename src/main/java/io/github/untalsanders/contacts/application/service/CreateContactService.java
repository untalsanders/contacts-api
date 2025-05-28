package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.domain.repository.ContactRepository;
import io.github.untalsanders.contacts.domain.usecase.CreateContactUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateContactService implements CreateContactUseCase {

    private final ContactRepository repository;

    @Autowired
    public CreateContactService(ContactRepository contactRepository) {
        this.repository = contactRepository;
    }

    @Override
    public Contact createContact(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Contact createContact(Long id, String firstName, String phoneNumber) {
        Contact contact = new Contact(id, firstName, phoneNumber);
        return repository.save(contact);
    }

    @Override
    public Contact createContact(Long id, String firstName, String lastName, String phoneNumber) {
        Contact contact = new Contact(id, firstName, lastName, phoneNumber);
        return repository.save(contact);
    }
}
