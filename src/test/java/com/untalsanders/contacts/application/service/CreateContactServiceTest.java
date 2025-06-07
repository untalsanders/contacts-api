package com.untalsanders.contacts.application.service;

import com.untalsanders.contacts.application.service.CreateContactService;
import com.untalsanders.contacts.domain.model.Contact;
import com.untalsanders.contacts.domain.repository.ContactRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private CreateContactService createContactService;

    @Test
    @DisplayName("Should create a contact")
    void should_create_contact() {
        Contact contact = new Contact(1L, "Sanders", "Gutiérrez", "1160219207");
        when(contactRepository.save(any())).thenReturn(contact);
        final Contact contactSaved = createContactService.createContact(contact);
        assertEquals(contact.getId(), contactSaved.getId());
        assertEquals(contact.getFirstname(), contactSaved.getFirstname());
        assertEquals(contact.getLastname(), contactSaved.getLastname());
        assertEquals(contact.getPhone(), contactSaved.getPhone());
    }
}
