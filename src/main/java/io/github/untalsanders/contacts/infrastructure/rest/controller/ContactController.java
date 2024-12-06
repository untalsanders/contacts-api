package io.github.untalsanders.contacts.infrastructure.rest.controller;

import io.github.untalsanders.contacts.application.service.CreateContactService;
import io.github.untalsanders.contacts.application.service.DeleteContactService;
import io.github.untalsanders.contacts.domain.usecase.RetrieveContactUseCase;
import io.github.untalsanders.contacts.domain.usecase.UpdateContactUseCase;
import io.github.untalsanders.contacts.domain.model.Contact;
import io.github.untalsanders.contacts.infrastructure.rest.dto.CreateContactDto;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ContactController {

    private final CreateContactService createContactService;
    private final RetrieveContactUseCase retrieveContactUseCase;
    private final UpdateContactUseCase updateContactUseCase;
    private final DeleteContactService deleteContactService;

    @Autowired
    public ContactController(
        CreateContactService createContactService,
        RetrieveContactUseCase retrieveContactUseCase,
        UpdateContactUseCase updateContactUseCase,
        DeleteContactService deleteContactService
    ) {
        this.createContactService = createContactService;
        this.retrieveContactUseCase = retrieveContactUseCase;
        this.updateContactUseCase = updateContactUseCase;
        this.deleteContactService = deleteContactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> retrieveAllContacts() {
        return new ResponseEntity<>(retrieveContactUseCase.getContacts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contact>> retrieveContactById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(retrieveContactUseCase.getContactById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody CreateContactDto dto) {
        Contact contact = new Contact();
        contact.setFirstname(dto.getFirstName());
        contact.setLastname(dto.getLastName());
        contact.setPhone(dto.getPhoneNumber());
        return new ResponseEntity<>(createContactService.createContact(contact), HttpStatus.CREATED);
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
