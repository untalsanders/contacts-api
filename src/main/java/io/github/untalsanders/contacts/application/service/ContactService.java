package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.infrastructure.persistence.ContactRepository;
import io.github.untalsanders.contacts.shared.exception.NoSuchElementFoundException;
import io.github.untalsanders.contacts.shared.exception.SuchElementAlreadyExistsException;
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
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(Long id) {
        Contact contact = contactRepository.findById(id);
        if (Objects.isNull(contact)) {
            throw new NoSuchElementFoundException(String.format("Contact %s not found", id));
        }
        return contact;
    }

    @Override
    public Contact save(Contact contact) {
        Contact contactToSave = contactRepository.findById(contact.id());
        if (Objects.nonNull(contactToSave)) {
            throw new SuchElementAlreadyExistsException(String.format("Contact %s it already exists", contactToSave));
        }
        contactRepository.save(contact);
        return getById(contact.id());
    }

    @Override
    public Contact update(Contact contact) {
        contactRepository.update(contact);
        return getById(contact.id());
    }

    @Override
    public void delete(Long id) {
        getById(id);
        contactRepository.deleteById(id);
    }
}
