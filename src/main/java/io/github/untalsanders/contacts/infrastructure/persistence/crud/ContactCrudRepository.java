package io.github.untalsanders.contacts.infrastructure.persistence.crud;

import io.github.untalsanders.contacts.infrastructure.persistence.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactCrudRepository extends JpaRepository<ContactEntity, Long> {}
