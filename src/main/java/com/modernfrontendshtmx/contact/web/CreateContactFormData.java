package com.modernfrontendshtmx.contact.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoDuplicateContactsByEmail
public class CreateContactFormData {
    @NotBlank
    private String givenName;
    @NotBlank
    private String familyName;
    @NotBlank
    private String phone;
    @Email
    private String email;
}
