package io.github.untalsanders.contacts.infrastructure.persistence.mapper;

import io.github.untalsanders.contacts.domain.Contact;
import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel="spring")
public interface ContactMapper {

    @Mappings({
            @Mapping(source="id", target="id"),
            @Mapping(source="name", target="name"),
    })
    Contact entityToDomain(ContactEntity contactEntity);

    List<Contact> toContacts(List<ContactEntity> contactEntities);

    @InheritInverseConfiguration
    ContactEntity domainToEntity(Contact contact);
}
