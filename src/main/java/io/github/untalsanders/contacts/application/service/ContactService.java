package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.infrastructure.persistence.JpaContactRepository;
import io.github.untalsanders.contacts.shared.exception.NoSuchElementFoundException;
import io.github.untalsanders.contacts.shared.exception.SuchElementAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ContactService implements ContactServicePort {

    private final JpaContactRepository jpaContactRepository;

    public ContactService(JpaContactRepository jpaContactRepository) {
        this.jpaContactRepository = jpaContactRepository;
    }

    @Override
    public List<Contact> getAll() {
        return jpaContactRepository.findAll();
    }

    @Override
    public Contact getById(Long id) {
        Contact contact = jpaContactRepository.findById(id);
        if (Objects.isNull(contact)) {
            throw new NoSuchElementFoundException(String.format("Contact %s not found", id));
        }
        return contact;
    }

    @Override
    public Contact save(Contact contact) {
        Contact contactToSave = jpaContactRepository.findById(contact.id());
        if (Objects.nonNull(contactToSave)) {
            throw new SuchElementAlreadyExistsException(String.format("Contact %s it already exists", contactToSave));
        }
        jpaContactRepository.save(contact);
        return getById(contact.id());
    }

    @Override
    public Contact update(Contact contact) {
        jpaContactRepository.update(contact);
        return getById(contact.id());
    }

    @Override
    public void delete(Long id) {
        getById(id);
        jpaContactRepository.deleteById(id);
    }
}
