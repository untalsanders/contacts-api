package io.github.untalsanders.contacts.infrastructure.persistence.util;

import io.github.untalsanders.contacts.infrastructure.persistence.crud.ContactCrudRepository;
import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulateDB implements InitializingBean {

    private final ContactCrudRepository contactCrudRepository;

    @Autowired
    public PopulateDB(ContactCrudRepository contactCrudRepository) {
        this.contactCrudRepository = contactCrudRepository;
    }

    @Override
    public void afterPropertiesSet() {
        contactCrudRepository.save(new ContactEntity(1L, "Contact #1"));
        contactCrudRepository.save(new ContactEntity(2L, "Contact #2"));
    }
}
