package com.modernfrontendshtmx.todomvc;

import java.util.List;
import org.springframework.data.repository.ListCrudRepository;

public interface SpringDataJpaTodoItemRepository
        extends ListCrudRepository<TodoItem, Long> {
    int countAllByCompleted(boolean completed);

    List<TodoItem> findAllByCompleted(boolean completed);
}
