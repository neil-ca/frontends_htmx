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

  public List<Contact> getAll() { return repository.findAll(); }
}
