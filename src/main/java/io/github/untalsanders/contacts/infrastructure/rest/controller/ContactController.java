package io.github.untalsanders.contacts.infrastructure.rest.controller;

import io.github.untalsanders.contacts.application.service.CreateContactService;
import io.github.untalsanders.contacts.application.service.RetrieveContactService;
import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.application.service.ContactServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactServicePort contactServicePort;
    private final CreateContactUseCase createContactUseCase;
    private final RetrieveContactUseCase retrieveContactUseCase;

    @Autowired
    public ContactController(
        ContactServicePort contactServicePort,
        CreateContactUseCase createContactUseCase,
        RetrieveContactUseCase retrieveContactUseCase
    ) {
        this.contactServicePort = contactServicePort;
        this.createContactUseCase = createContactUseCase;
        this.retrieveContactUseCase = retrieveContactUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return new ResponseEntity<>(retrieveContactUseCase.getContacts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contact>> getContactById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(retrieveContactUseCase.getContact(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(createContactUseCase.createContact(contact), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactServicePort.update(contact), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteContactById(@PathVariable("id") Long id) {
        contactServicePort.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
