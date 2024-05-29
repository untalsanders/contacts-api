package io.github.untalsanders.contacts.domain.service;

import io.github.untalsanders.contacts.domain.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();
    Contact getById(Long contactId);
    Contact save(Contact contact);
    Contact update(Contact contact);
    void delete(Long contactId);
}
