package com.modernfrontendshtmx.contact.repository;

import com.modernfrontendshtmx.contact.Contact;
import com.modernfrontendshtmx.contact.ContactId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryContactRepository implements ContactRepository {
  private final Map<ContactId, Contact> values = new HashMap<>();

  public InMemoryContactRepository() {
    values.putAll(
        Stream
            .of(new Contact(new ContactId(1L), "Wim", "Deblauwe", "555-789-999",
                            "wim@example.com"),
                new Contact(new ContactId(2L), "John", "Doe", "555-123-456",
                            "john@example.com"),
                new Contact(new ContactId(3L), "Ada", "Lovelace", "555-873-321",
                            "ada@lovelace.com"))
            .collect(Collectors.toMap(Contact::getId, Function.identity())));
  }

  @Override
  public List<Contact> findAll() {
    return List.copyOf(values.values());
  }
}
