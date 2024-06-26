package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.domain.model.Contact;

import java.util.List;

public interface ContactServicePort {
    List<Contact> getAll();
    Contact getById(Long id);
    Contact save(Contact contact);
    Contact update(Contact contact);
    void delete(Long id);
}
