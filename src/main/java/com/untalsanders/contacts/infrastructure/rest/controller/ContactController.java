package com.untalsanders.contacts.infrastructure.rest.controller;

import com.untalsanders.contacts.application.service.DeleteContactService;
import com.untalsanders.contacts.application.usecase.CreateContactUseCase;
import com.untalsanders.contacts.application.usecase.RetrieveContactUseCase;
import com.untalsanders.contacts.application.usecase.UpdateContactUseCase;
import com.untalsanders.contacts.domain.model.Contact;
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

    private final CreateContactUseCase createContactUseCase;
    private final RetrieveContactUseCase retrieveContactUseCase;
    private final UpdateContactUseCase updateContactUseCase;
    private final DeleteContactService deleteContactService;

    public ContactController(
        CreateContactUseCase createContactUseCase,
        RetrieveContactUseCase retrieveContactUseCase,
        UpdateContactUseCase updateContactUseCase,
        DeleteContactService deleteContactService
    ) {
        this.createContactUseCase = createContactUseCase;
        this.retrieveContactUseCase = retrieveContactUseCase;
        this.updateContactUseCase = updateContactUseCase;
        this.deleteContactService = deleteContactService;
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

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Contact>> updateContact(@PathVariable("id") Long id, @RequestBody Contact contact) {
        return new ResponseEntity<>(updateContactUseCase.updateContact(id, contact), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteContactById(@PathVariable("id") Long id) {
        deleteContactService.deleteContact(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
