package io.github.untalsanders.contacts.infrastructure.persistence;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.domain.repository.ContactRepository;
import io.github.untalsanders.contacts.infrastructure.persistence.crud.ContactCrudRepository;
import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import io.github.untalsanders.contacts.infrastructure.persistence.mapper.ContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaContactRepository implements ContactRepository {

    private static final Logger log = LoggerFactory.getLogger(JpaContactRepository.class);
    private final ContactCrudRepository contactCrudRepository;
    private final ContactMapper contactMapper;

    public JpaContactRepository(ContactCrudRepository contactCrudRepository, ContactMapper contactMapper) {
        this.contactCrudRepository = contactCrudRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public Contact findById(Long id) {
        Contact contactFound = contactCrudRepository.findById(id).map(contactMapper::entityToDomain).orElse(null);
        log.info("Contact found: {}", contactFound);
        return contactFound;
    }

    @Override
    public List<Contact> findAll() {
        List<ContactEntity> contactEntityList = (List<ContactEntity>) contactCrudRepository.findAll();
        return contactMapper.toContacts(contactEntityList);
    }

    @Override
    public Contact save(Contact contact) {
        ContactEntity contactEntity = contactMapper.domainToEntity(contact);
        Contact contactSaved = contactMapper.entityToDomain(contactCrudRepository.save(contactEntity));
        log.info("Contact created: {}", contactSaved);
        return contactSaved;
    }

    @Override
    public void update(Contact contact) {
        ContactEntity contactEntity = contactMapper.domainToEntity(contact);
        contactEntity.setId(contact.getId());
        contactEntity.setName(contact.getName());
        contactEntity.setPhone(contact.getPhone());
        save(contactMapper.entityToDomain(contactEntity));
    }

    @Override
    public void delete(Contact contact) {
        log.info("Deleting contact: {}", contact.getId());
    }

    @Override
    public void deleteById(Long id) {
        Contact contact = findById(id);
        log.info("Deleting contact by ID: {}", contact);
        contactCrudRepository.deleteById(contact.getId());
    }
}
