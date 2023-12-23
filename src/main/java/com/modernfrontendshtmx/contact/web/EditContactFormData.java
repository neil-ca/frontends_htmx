package com.modernfrontendshtmx.contact.web;

import com.modernfrontendshtmx.contact.Contact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditContactFormData {
  private long id;
  @NotBlank private String givenName;
  @NotBlank private String familyName;
  @NotBlank private String phone;
  @Email private String email;

  public static EditContactFormData from(Contact contact) {
    EditContactFormData formData = new EditContactFormData();
    formData.setId(contact.getId().value);
    formData.setGivenName(contact.getGivenName());
    formData.setFamilyName(contact.getFamilyName());
    formData.setPhone(contact.getPhone());
    formData.setEmail(contact.getEmail());
    return formData;
  }
}
