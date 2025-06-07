package com.untalsanders.contacts.infrastructure.mapper;

import com.untalsanders.contacts.domain.model.Contact;
import com.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import com.untalsanders.contacts.rest.dto.ContactDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    @Mapping(target = "id", ignore = true)
    Contact toContact(ContactDto contactDto);

    @InheritInverseConfiguration
    ContactDto toContactDto(Contact contact);

    Contact entityToDomain(ContactEntity contactEntity);

    List<Contact> toContacts(List<ContactEntity> contactEntities);

    @InheritInverseConfiguration
    ContactEntity domainToEntity(Contact contact);
}
