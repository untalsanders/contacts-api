package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.application.usecase.CreateContactUseCase;
import io.github.untalsanders.contacts.infrastructure.persistence.JpaContactRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateContactService implements CreateContactUseCase {

    private final JpaContactRepository repository;

    public CreateContactService(JpaContactRepository jpaContactRepository) {
        this.repository = jpaContactRepository;
    }

    @Override
    public Contact createContact(Contact contact) {
        return repository.save(contact);
    }
}
