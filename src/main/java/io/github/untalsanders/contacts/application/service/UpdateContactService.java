package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.domain.usecase.UpdateContactUseCase;
import io.github.untalsanders.contacts.domain.exception.ContactNotFoundException;
import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.domain.repository.ContactRepository;
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
            throw new ContactNotFoundException(String.format("Contact not found with id %s", id));
        }

        Contact existingContact = contactToUpdate.get();
        existingContact.setFirstname(contact.getFirstname());
        existingContact.setLastname(contact.getLastname());
        existingContact.setPhone(contact.getPhone());

        return Optional.of(contactRepository.update(id, existingContact));
    }
}
