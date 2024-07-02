package io.github.untalsanders.contacts.infrastructure.persistence.mapper;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContactMapperTest {
    @Test
    @DisplayName("Should map to contact entity")
    void should_map_to_contact_entity() {
        // Given
        Contact contact = new Contact(1L, "Dayhana", "1133052795");
        // When
        ContactEntity contactEntity = ContactMapper.INSTANCE.domainToEntity(contact);
        // Then
        assertThat(contactEntity).isNotNull();
        assertThat(contactEntity.getFirstname()).isEqualTo(contact.getFirstname());
        assertThat(contactEntity.getLastname()).isEqualTo(contact.getLastname());
    }
}
