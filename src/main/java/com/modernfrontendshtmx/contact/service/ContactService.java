package com.modernfrontendshtmx.contact.service;

import com.modernfrontendshtmx.contact.Contact;
import com.modernfrontendshtmx.contact.repository.ContactRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository repository;

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact storeNewContact(String givenName, String familyName,
            String phone, String email) {
        Contact contact = new Contact(repository.nextId(), givenName, familyName, phone, email);
        repository.save(contact);
        return contact;
    }
}
