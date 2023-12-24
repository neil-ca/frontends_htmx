package com.modernfrontendshtmx.contact.repository;

import com.modernfrontendshtmx.contact.Contact;
import com.modernfrontendshtmx.contact.ContactId;
import java.util.List;
import java.util.Optional;

public interface ContactRepository {
  ContactId nextId();

  List<Contact> findAll();

  void save(Contact contact);

  List<Contact> findAllWithNameContaining(String query);

  Optional<Contact> findById(ContactId contactId);

  void deleteById(ContactId contactId);

  boolean existsByEmail(String email);

  Page<Contact> findAllOrderedByName(int page, int size);
}
