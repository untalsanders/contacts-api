package io.github.untalsanders.contacts.infrastructure.persistence.mapper;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact entityToDomain(ContactEntity contactEntity);

    List<Contact> toContacts(List<ContactEntity> contactEntities);

    @InheritInverseConfiguration
    ContactEntity domainToEntity(Contact contact);
}
