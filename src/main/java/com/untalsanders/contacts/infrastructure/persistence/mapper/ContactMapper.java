package com.untalsanders.contacts.infrastructure.persistence.mapper;

import com.untalsanders.contacts.domain.model.Contact;
import com.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
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
