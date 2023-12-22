package com.modernfrontendshtmx.contact.web;

import com.modernfrontendshtmx.contact.Contact;
import com.modernfrontendshtmx.contact.service.ContactService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService service;

    @GetMapping
    public String viewContacts(Model model,
            @RequestParam(value = "q", required = false) String query) {
        List<Contact> contactList;
        if (query != null) {
            model.addAttribute("contacts", query);
            contactList = service.searchContacts(query);
        } else {
            contactList = service.getAll();
        }
        model.addAttribute("contacts", contactList);
        return "contacts/list";
    }

    @GetMapping("/new")
    public String newContact(Model model) {
        model.addAttribute("formData", new CreateContactFormData());
        return "contacts/edit";
    }

    @PostMapping("/new")
    public String createNewContact(Model model,
            @ModelAttribute("formData") @Valid CreateContactFormData formData,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "contact/edit";
        }

        service.storeNewContact(formData.getGivenName(), formData.getFamilyName(),
                formData.getPhone(), formData.getEmail());

        return "redirect:/contacts";
    }
}
