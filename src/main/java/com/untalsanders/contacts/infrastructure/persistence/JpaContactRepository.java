package com.untalsanders.contacts.infrastructure.persistence;

import com.untalsanders.contacts.domain.model.Contact;
import com.untalsanders.contacts.domain.repository.ContactRepository;
import com.untalsanders.contacts.infrastructure.mapper.ContactMapper;
import com.untalsanders.contacts.infrastructure.persistence.crud.ContactCrudRepository;
import com.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaContactRepository implements ContactRepository {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(JpaContactRepository.class);
    private final ContactCrudRepository contactCrudRepository;
    private final ContactMapper contactMapper;

    public JpaContactRepository(ContactCrudRepository contactCrudRepository, ContactMapper contactMapper) {
        this.contactCrudRepository = contactCrudRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public Optional<Contact> findById(Long id) {
        Contact contactFound = contactCrudRepository.findById(id).map(contactMapper::entityToDomain).orElse(null);
        LOG.info("Contact found: {}", contactFound);
        return Optional.ofNullable(contactFound);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Contact> findAll() {
        Query query = this.em.createQuery("SELECT contact FROM ContactEntity contact");
        Collection<ContactEntity> contactEntityCollection = query.getResultList();
        LOG.info("Total contacts found: {}", contactEntityCollection.size());
        return contactMapper.toContacts(contactEntityCollection.stream().toList());
    }

    @Override
    public Contact save(Contact contact) {
        ContactEntity contactSaved = contactCrudRepository.save(contactMapper.domainToEntity(contact));
        LOG.info("Contact created: {}", contactSaved);
        return contactMapper.entityToDomain(contactSaved);
    }

    @Override
    public Contact update(Long id, Contact contact) {
        ContactEntity contactEntity = contactMapper.domainToEntity(contact);
        contactEntity.setId(contact.getId());
        contactEntity.setFirstname(contact.getFirstname());
        contactEntity.setLastname(contact.getLastname());
        contactEntity.setPhone(contact.getPhone());
        return save(contactMapper.entityToDomain(contactEntity));
    }

    @Override
    public void delete(Contact contact) {
        LOG.info("Deleting contact: {}", contact.getId());
    }

    @Override
    public void deleteById(Long id) {
        Optional<Contact> contact = findById(id);
        contact.ifPresent(value -> {
            contactCrudRepository.deleteById(value.getId());
            LOG.info("Deleted Contact with ID: {}", contact);
        });
    }
}
