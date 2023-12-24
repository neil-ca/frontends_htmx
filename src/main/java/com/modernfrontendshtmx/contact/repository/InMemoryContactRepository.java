package com.modernfrontendshtmx.contact.repository;

import com.modernfrontendshtmx.contact.Contact;
import com.modernfrontendshtmx.contact.ContactId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryContactRepository implements ContactRepository {
  private final AtomicLong secuence = new AtomicLong();
  private final Map<ContactId, Contact> values = new HashMap<>();

  public InMemoryContactRepository() {
    List.of(new Contact(nextId(), "Wim", "Deblauwe", "555-789-999",
                        "wim@example.com"),
            new Contact(nextId(), "John", "Doe", "555-123-456",
                        "john@example.com"),
            new Contact(nextId(), "Ada", "Lovelace", "555-873-321",
                        "ada@lovelace.com"))
        .forEach(this::save);
  }

  @Override
  public ContactId nextId() {
    return new ContactId(secuence.incrementAndGet());
  }

  @Override
  public List<Contact> findAll() {
    return List.copyOf(values.values());
  }

  @Override
  public void save(Contact contact) {
    values.put(contact.getId(), contact);
  }

  @Override
  public List<Contact> findAllWithNameContaining(String query) {
    return values.values()
        .stream()
        .filter(contact -> contact.hasName(query))
        .toList();
  }

  @Override
  public Optional<Contact> findById(ContactId contactId) {
    return Optional.ofNullable(values.get(contactId));
  }

  @Override
  public void deleteById(ContactId contactId) {
    values.remove(contactId);
  }

  @Override
  public boolean existsByEmail(String email) {
    return values.values().stream().anyMatch(
        contact -> contact.getEmail().equals(email));
  }
}
