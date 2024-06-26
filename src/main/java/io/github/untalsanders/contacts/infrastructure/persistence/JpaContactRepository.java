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
        return contactCrudRepository.findById(id).map(contactMapper::entityToDomain).orElse(null);
    }

    @Override
    public List<Contact> findAll() {
        List<ContactEntity> contactEntityList = (List<ContactEntity>) contactCrudRepository.findAll();
        return contactMapper.toContacts(contactEntityList);
    }

    @Override
    public Contact save(Contact contact) {
        ContactEntity contactEntity = contactMapper.domainToEntity(contact);
        return contactMapper.entityToDomain(contactCrudRepository.save(contactEntity));
    }

    @Override
    public void update(Contact contact) {
        ContactEntity contactEntity = contactMapper.domainToEntity(contact);
        contactEntity.setId(contact.id());
        contactEntity.setName(contact.name());
        contactEntity.setPhone(contact.phone());
        save(contactMapper.entityToDomain(contactEntity));
    }

    @Override
    public void delete(Contact contact) {
        log.info("Deleting contact: {}", contact.id());
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting contact by ID: {}", id);
        Contact contact = findById(id);
        contactCrudRepository.deleteById(contact.id());
    }
}
