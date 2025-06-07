package com.untalsanders.contacts.infrastructure.persistence.crud;

import com.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactCrudRepository extends CrudRepository<ContactEntity, Long> {}
