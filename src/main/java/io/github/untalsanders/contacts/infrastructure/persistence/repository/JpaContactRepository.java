package io.github.untalsanders.contacts.infrastructure.persistence.repository;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.domain.repository.ContactRepository;
import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import io.github.untalsanders.contacts.infrastructure.persistence.mapper.ContactMapper;
import io.github.untalsanders.contacts.infrastructure.persistence.repository.crud.ContactListCrudRepository;
import io.github.untalsanders.contacts.shared.infrastructure.rest.exception.SuchElementAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Slf4j
public class JpaContactRepository implements ContactRepository {

    private final ContactListCrudRepository contactListCrudRepository;
    private final ContactMapper contactMapper;

    public JpaContactRepository(ContactListCrudRepository contactListCrudRepository, ContactMapper contactMapper) {
        this.contactListCrudRepository = contactListCrudRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public Optional<Contact> findById(Long id) {
        Contact contactFound = contactListCrudRepository
            .findById(id)
            .map(contactMapper::entityToDomain)
            .orElse(null);

        if (Objects.nonNull(contactFound)) {
            log.info("Contact found: {}", contactFound);
        }

        return Optional.ofNullable(contactFound);
    }

    @Override
    public List<Contact> findAll() {
        List<ContactEntity> contactEntityList = contactListCrudRepository.findAll();
        log.info("Total contacts found: {}", contactEntityList.size());
        return contactMapper.toContacts(contactEntityList);
    }

    @Override
    public Contact save(Contact contact) {
//        Optional<Contact> contactToSave = findById(contact.getId());

//        if (contactToSave.isPresent()) {
//            throw new SuchElementAlreadyExistsException("Contact with id " + contact.getId() + " already exists");
//        }

        ContactEntity contactEntitySaved = contactListCrudRepository.save(contactMapper.domainToEntity(contact));
        log.info("Contact created: {}", contactEntitySaved);
        return contactMapper.entityToDomain(contactEntitySaved);
    }

    @Override
    public Contact update(Long id, Contact contact) {
        ContactEntity contactEntity = contactMapper.domainToEntity(contact);
        Contact contactUpdated = save(contactMapper.entityToDomain(contactEntity));
        log.info("Contact updated: {}", contactUpdated);
        return contactUpdated;
    }

    @Override
    public void delete(Contact contact) {
        log.info("Deleting contact: {}", contact.getId());
    }

    @Override
    public void deleteById(Long id) {
        Optional<Contact> contact = findById(id);
        contact.ifPresent(value -> {
            contactListCrudRepository.deleteById(value.getId());
            log.info("Deleted Contact with ID: {}", contact);
        });
    }
}
