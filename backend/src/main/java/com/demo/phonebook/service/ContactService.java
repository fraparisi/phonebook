package com.demo.phonebook.service;

import com.demo.phonebook.entity.Contact;
import com.demo.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Iterable<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact addContact(Contact employee) {
        return contactRepository.save(employee);
    }

    public Optional<Contact> updateContact(Long id, Contact contact) {
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            Contact updatedContact = existingContact.get();
            updatedContact.setName(contact.getName());
            updatedContact.setSurname(contact.getSurname());
            updatedContact.setAddress(contact.getAddress());
            updatedContact.setLat(contact.getLat());
            updatedContact.setLon(contact.getLon());
            updatedContact.setPhone(contact.getPhone());
            updatedContact.setOther(contact.getOther());

            contactRepository.save(updatedContact);
        }
        return existingContact;
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public List<Contact> search(String keyword) {
        return contactRepository.search(keyword);
    }

    public List<Contact> searchWithPhone(String keyword, String phone) {
        return contactRepository.searchWithPhone(keyword, phone);
    }

    public List<Contact> findBydPhone(String phone) {
        return contactRepository.findByPhone(phone);
    }
}
