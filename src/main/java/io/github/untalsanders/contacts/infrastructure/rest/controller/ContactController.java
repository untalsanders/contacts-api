package io.github.untalsanders.contacts.infrastructure.rest.controller;

import io.github.untalsanders.contacts.application.service.CreateContactService;
import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.application.service.ContactServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactServicePort contactServicePort;
    private final CreateContactService createContactService;

    @Autowired
    public ContactController(ContactServicePort contactServicePort, CreateContactService createContactService) {
        this.contactServicePort = contactServicePort;
        this.createContactService = createContactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return new ResponseEntity<>(contactServicePort.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<Contact> getContactById(@PathVariable("contactId") Long contactId) {
        return new ResponseEntity<>(contactServicePort.getById(contactId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(createContactService.createContact(contact), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactServicePort.update(contact), HttpStatus.CREATED);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Map<String, Boolean>> deleteContactById(@PathVariable("contactId") Long contactId) {
        contactServicePort.delete(contactId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
