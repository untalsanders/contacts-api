package com.untalsanders.contacts.application.service;

import com.untalsanders.contacts.application.usecase.DeleteContactUseCase;
import com.untalsanders.contacts.domain.repository.ContactRepository;
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
