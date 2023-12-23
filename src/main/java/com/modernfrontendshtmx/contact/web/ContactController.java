package com.modernfrontendshtmx.contact.web;

import com.modernfrontendshtmx.contact.Contact;
import com.modernfrontendshtmx.contact.ContactId;
import com.modernfrontendshtmx.contact.service.ContactService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
  private final ContactService service;

  @GetMapping
  public String viewContacts(Model model,
                             @RequestParam(value = "q",
                                           required = false) String query) {
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

  @GetMapping("/{id}")
  public String viewContact(Model model, @PathVariable("id") long id) {
    Contact contact = service.getContact(new ContactId(id));
    model.addAttribute("contact", contact);
    return "contacts/view";
  }

  @GetMapping("/new")
  public String newContact(Model model) {
    model.addAttribute("formData", new CreateContactFormData());
    model.addAttribute("editMode", EditMode.CREATE);
    return "contacts/edit";
  }

  @PostMapping("/new")
  public String createNewContact(Model model,
                                 @ModelAttribute("formData")
                                 @Valid CreateContactFormData formData,
                                 BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("editMode", EditMode.CREATE);
      return "contacts/edit";
    }

    service.storeNewContact(formData.getGivenName(), formData.getFamilyName(),
                            formData.getPhone(), formData.getEmail());

    return "redirect:/contacts";
  }

  @GetMapping("/{id}/edit")
  public String editContact(Model model, @PathVariable("id") long id) {
    Contact contact = service.getContact(new ContactId(id));
    model.addAttribute("formData", EditContactFormData.from(contact));
    model.addAttribute("editMode", EditMode.UPDATE);
    return "contacts/edit";
  }

  public String doEditContact(Model model, @PathVariable("id") long id,
                              @ModelAttribute("formData")
                              @Valid EditContactFormData formData,
                              BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("editMode", EditMode.UPDATE);
      return "contacts/edit";
    }

    service.updateContact(new ContactId(id), formData.getGivenName(),
                          formData.getFamilyName(), formData.getPhone(),
                          formData.getEmail());
    return "redirect:/contacts";
  }

  @DeleteMapping("/{id}")
  public RedirectView deleteContact(@PathVariable("id") long id,
                                    RedirectAttributes redirectAttributes) {
    service.deleteContact(new ContactId(id));
    redirectAttributes.addFlashAttribute("successMessage", "Deleted Contact!");
    RedirectView redirectView = new RedirectView("/contacts");
    redirectView.setStatusCode(HttpStatus.SEE_OTHER);
    return redirectView;
  }
}
