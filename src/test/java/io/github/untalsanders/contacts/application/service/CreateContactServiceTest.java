package io.github.untalsanders.contacts.application.service;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.domain.repository.ContactRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private CreateContactService createContactService;

    @Test
    @DisplayName("Should create a contact")
    void shouldCreateContact() {
        Contact contactToSave = new Contact(1L, "Sanders", "Gutiérrez", "1160219207");
        when(contactRepository.save(contactToSave)).thenReturn(new Contact(1L, "Sanders", "Gutiérrez", "1160219207"));
        final Contact contactSaved = createContactService.createContact(contactToSave);
        assertEquals(contactToSave.getId(), contactSaved.getId());
        assertEquals(contactToSave.getFirstname(), contactSaved.getFirstname());
        assertEquals(contactToSave.getLastname(), contactSaved.getLastname());
        assertEquals(contactToSave.getPhone(), contactSaved.getPhone());
    }
}
