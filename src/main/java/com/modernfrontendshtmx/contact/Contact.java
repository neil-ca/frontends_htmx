package com.modernfrontendshtmx.contact;

import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Contact {
    private final ContactId id;
    private String givenName;
    private String familyName;
    private String phone;
    private String email;

    public boolean hasName(String name) {
        return givenName.toLowerCase(Locale.ROOT).contains(name) ||
                familyName.toLowerCase(Locale.ROOT).contains(name);
    }
}
