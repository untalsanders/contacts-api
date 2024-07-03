package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.application.usecase.DeleteContactUseCase;
import io.github.untalsanders.contacts.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteContactService implements DeleteContactUseCase {
    private final ContactRepository contactRepository;

    public DeleteContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
