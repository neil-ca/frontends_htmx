package com.modernfrontendshtmx.contact.web;

import com.modernfrontendshtmx.contact.Contact;
import com.modernfrontendshtmx.contact.service.ContactService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
  private final ContactService service;

  @GetMapping
  public String viewContacts(Model model) {
    List<Contact> contactList = service.getAll();
    model.addAttribute("contacts", contactList);
    return "contacts/list";
  }
}
