package com.untalsanders.contacts.infrastructure.persistence.util;

import com.untalsanders.contacts.domain.model.Contact;
import com.untalsanders.contacts.infrastructure.persistence.JpaContactRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ContactPopulateDB implements InitializingBean {

    private final JpaContactRepository jpaContactRepository;

    @Autowired
    public ContactPopulateDB(JpaContactRepository jpaContactRepository) {
        this.jpaContactRepository = jpaContactRepository;
    }

    @Override
    public void afterPropertiesSet() {
        Contact c1 = new Contact(1L, "Dayhana", "1133052795");
        Contact c2 = new Contact(2L, "Sanders", "1160219207");
        Set<Contact> contactList = new HashSet<>();
        contactList.add(c1);
        contactList.add(c2);
        for (Contact contact : contactList) {
            jpaContactRepository.save(contact);
        }
    }
}
