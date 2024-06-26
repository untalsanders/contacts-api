package io.github.untalsanders.contacts.infrastructure.persistence.util;

import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.infrastructure.persistence.JpaContactRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactPopulateDB implements InitializingBean {

    private final JpaContactRepository jpaContactRepository;

    @Autowired
    public ContactPopulateDB(JpaContactRepository jpaContactRepository) {
        this.jpaContactRepository = jpaContactRepository;
    }

    @Override
    public void afterPropertiesSet() {
        jpaContactRepository.save(new Contact("Dayhana", "113052795"));
        jpaContactRepository.save(new Contact("Mamá", "3112828304"));
    }
}
