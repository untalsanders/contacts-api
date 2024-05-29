package io.github.untalsanders.contacts.application.rest.controller;

import io.github.untalsanders.contacts.domain.Contact;
import io.github.untalsanders.contacts.domain.service.ContactService;
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

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAll() {
        return new ResponseEntity<>(contactService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<Contact> getById(@PathVariable("contactId") Long contactId) {
        return new ResponseEntity<>(contactService.getById(contactId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> save(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.save(contact), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Contact> update(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.update(contact), HttpStatus.CREATED);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("contactId") Long contactId) {
        contactService.delete(contactId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
