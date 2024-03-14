package io.github.untalsanders.contacts.domain.repository;

import io.github.untalsanders.contacts.domain.Contact;

import java.util.List;

public interface ContactRepository {
    List<Contact> getAll();
    Contact getById(Long contactId);
    Contact save(Contact contact);
    Contact update(Contact contact);
    void delete(Long contactId);
}
