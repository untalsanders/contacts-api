package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.application.usecase.UpdateContactUseCase;
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
    public Optional<Contact> updateContact(Long id, Contact contact) {
        return Optional.of(contactRepository.update(id, contact));
    }
}
