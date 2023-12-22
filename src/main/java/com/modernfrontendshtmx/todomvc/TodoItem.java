package com.modernfrontendshtmx.todomvc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TodoItem {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    private boolean completed;

    protected TodoItem() {
    }

    public TodoItem(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }
}
