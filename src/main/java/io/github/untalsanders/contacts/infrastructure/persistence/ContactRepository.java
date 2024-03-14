package io.github.untalsanders.contacts.infrastructure.persistence;

import io.github.untalsanders.contacts.domain.Contact;
import io.github.untalsanders.contacts.hotel.application.port.out.ContactRepositoryPort;
import io.github.untalsanders.contacts.infrastructure.persistence.crud.ContactCrudRepository;
import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import io.github.untalsanders.contacts.infrastructure.persistence.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository implements ContactRepositoryPort {

    private final ContactCrudRepository contactCrudRepository;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactRepository(ContactCrudRepository contactCrudRepository, ContactMapper contactMapper) {
        this.contactCrudRepository = contactCrudRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public List<Contact> getAll() {
        List<ContactEntity> contactEntityList = contactCrudRepository.findAll();
        return contactMapper.toContacts(contactEntityList);
    }

    @Override
    public Contact getById(Long hotelId) {
        return contactCrudRepository.findById(hotelId).map(contactMapper::entityToDomain).orElse(null);
    }

    @Override
    public Contact save(Contact contact) {
        ContactEntity contactEntity = contactMapper.domainToEntity(contact);
        return contactMapper.entityToDomain(contactCrudRepository.save(contactEntity));
    }

    @Override
    public Contact update(Contact contact) {
        ContactEntity contactEntity = contactMapper.domainToEntity(getById(contact.getId()));
        contactEntity.setId(contact.getId());
        contactEntity.setName(contact.getName());
        return save(contactMapper.entityToDomain(contactEntity));
    }

    @Override
    public void delete(Long contactId) {
        Contact contact = getById(contactId);
        contactCrudRepository.deleteById(contact.getId());
    }
}
