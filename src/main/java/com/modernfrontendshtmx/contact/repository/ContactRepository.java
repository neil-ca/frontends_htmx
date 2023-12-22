package com.modernfrontendshtmx.contact.repository;

import java.util.List;

import com.modernfrontendshtmx.contact.Contact;

public interface ContactRepository {
  List<Contact> findAll();
}
