package com.untalsanders.contacts.infrastructure.persistence.mapper;

import com.untalsanders.contacts.domain.model.Contact;
import com.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import com.untalsanders.contacts.infrastructure.persistence.mapper.ContactMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContactMapperTest {
    @Test
    @DisplayName("Should map to contact entity")
    void should_map_to_contact_entity() {
        Contact contact = new Contact(1L, "Dayhana", "1133052795");
        ContactEntity contactEntity = ContactMapper.INSTANCE.domainToEntity(contact);
        assertThat(contactEntity).isNotNull();
        assertThat(contactEntity.getFirstname()).isEqualTo(contact.getFirstname());
        assertThat(contactEntity.getLastname()).isEqualTo(contact.getLastname());
    }
}
