package com.modernfrontendshtmx.contact.repository;

import com.modernfrontendshtmx.contact.Contact;
import com.modernfrontendshtmx.contact.ContactId;
import java.util.List;

public interface ContactRepository {
    ContactId nextId();

    List<Contact> findAll();

    void save(Contact contact);
}
