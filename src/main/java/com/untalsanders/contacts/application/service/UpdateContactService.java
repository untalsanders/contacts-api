package com.untalsanders.contacts.application.service;

import com.untalsanders.contacts.application.usecase.UpdateContactUseCase;
import com.untalsanders.contacts.domain.exception.ContactNotFoundException;
import com.untalsanders.contacts.domain.model.Contact;
import com.untalsanders.contacts.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateContactService implements UpdateContactUseCase {
    private final ContactRepository contactRepository;

    public UpdateContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Optional<Contact> updateContact(Long id, Contact contact) throws ContactNotFoundException {
        if (contact.getId() == null) {
            contact.setId(id);
        }

        if (!id.equals(contact.getId())) {
            throw new IllegalArgumentException(
                String.format("Path variable /id=%s no match with Contact[id=%s]", id, contact.getId())
            );
        }

        Optional<Contact> contactToUpdate = contactRepository.findById(id);
        if (contactToUpdate.isEmpty()) {
            throw new ContactNotFoundException(String.format("Contact with id %s not found", id));
        }

        return Optional.of(contactRepository.update(id, contact));
    }
}
