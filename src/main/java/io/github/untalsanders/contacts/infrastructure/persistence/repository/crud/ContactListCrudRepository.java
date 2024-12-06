package io.github.untalsanders.contacts.infrastructure.persistence.repository.crud;

import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ContactListCrudRepository extends ListCrudRepository<ContactEntity, Long> {}
