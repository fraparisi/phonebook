package com.demo.phonebook.web;

import com.demo.phonebook.entity.Contact;
import com.demo.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public Iterable<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getEmployeeById(@PathVariable Long id) {
        Optional<Contact> employee = contactService.getContactById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Contact> addEmployee(@RequestBody Contact contact) {
        Contact savedEmployee = contactService.addContact(contact);
        return ResponseEntity.created(URI.create("/api/contacts/" + savedEmployee.getId())).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateEmployee(@PathVariable Long id,
                                                  @RequestBody Contact contact) {
        Optional<Contact> existingContact = contactService.updateContact(id,
                contact);
        return existingContact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Iterable<Contact> search(@RequestParam String keyword, @RequestParam String phone) {
        if(!Objects.equals(keyword, "") && !Objects.equals(phone, "")) {
            return contactService.searchWithPhone(keyword, phone);
        }
        if(!Objects.equals(keyword, "") && Objects.equals(phone, "")) {
            return contactService.search(keyword);
        }
        if(Objects.equals(keyword, "") && !Objects.equals(phone, "")) {
            return contactService.findBydPhone(phone);
        }
        return this.getAllContacts();
    }
}
