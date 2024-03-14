package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.domain.Contact;
import io.github.untalsanders.contacts.infrastructure.persistence.ContactRepository;
import io.github.untalsanders.contacts.shared.exception.SuchElementAlreadyExistsException;
import io.github.untalsanders.contacts.shared.exception.NoSuchElementFoundException;
import io.github.untalsanders.contacts.hotel.application.port.in.ContactServicePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContactService implements ContactServicePort {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.getAll();
    }

    @Override
    public Contact getById(Long contactId) {
        Contact contact = contactRepository.getById(contactId);
        if (Objects.isNull(contact)) {
            throw new NoSuchElementFoundException(String.format("Contact %s not found", contactId));
        }
        return contact;
    }

    @Override
    public Contact save(Contact contact) {
        Contact contactToSave = contactRepository.getById(contact.getId());
        if (Objects.nonNull(contactToSave)) {
            throw new SuchElementAlreadyExistsException(String.format("Contact %s it already exists", contactToSave));
        }
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        getById(contact.getId());
        return contactRepository.update(contact);
    }

    @Override
    public void delete(Long contactId) {
        getById(contactId);
        contactRepository.delete(contactId);
    }
}
