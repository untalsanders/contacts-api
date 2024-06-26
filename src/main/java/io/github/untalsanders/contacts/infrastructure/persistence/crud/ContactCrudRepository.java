package io.github.untalsanders.contacts.infrastructure.persistence.crud;

import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactCrudRepository extends CrudRepository<ContactEntity, Long> {}
