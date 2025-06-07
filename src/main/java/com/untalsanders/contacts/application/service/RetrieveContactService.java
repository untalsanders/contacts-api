package com.untalsanders.contacts.application.service;

import com.untalsanders.contacts.application.usecase.RetrieveContactUseCase;
import com.untalsanders.contacts.domain.exception.ContactNotFoundException;
import com.untalsanders.contacts.domain.model.Contact;
import com.untalsanders.contacts.domain.repository.ContactRepository;
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
