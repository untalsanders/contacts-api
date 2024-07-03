package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.application.usecase.RetrieveContactUseCase;
import io.github.untalsanders.contacts.domain.exception.ContactNotFoundException;
import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetrieveContactService implements RetrieveContactUseCase {
    private final ContactRepository contactRepository;

    public RetrieveContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Optional<Contact> getContact(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new ContactNotFoundException(String.format("Contact with id %s not found", id));
        }
        return contact;
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }
}
