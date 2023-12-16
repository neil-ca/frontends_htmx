package com.modernfrontendshtmx.todomvc.web;

import com.modernfrontendshtmx.todomvc.TodoItem;
import com.modernfrontendshtmx.todomvc.TodoItemNotFoundException;
import com.modernfrontendshtmx.todomvc.TodoItemRepository;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoItemController {
  private final TodoItemRepository repository;

  public TodoItemController(TodoItemRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public String todo(Model model) {
    addAttributesForIndex(model, ListFilter.ALL);
    return "todo";
  }

  @GetMapping("/active")
  public String todoActive(Model model) {
    addAttributesForIndex(model, ListFilter.ACTIVE);
    return "todo";
  }

  @GetMapping("/completed")
  public String todoCompleted(Model model) {
    addAttributesForIndex(model, ListFilter.COMPLETED);
    return "todo";
  }

  @PostMapping
  @HxRequest
  public String addNewTodoItem(TodoItemFormData formData, Model model) {
    TodoItem item = repository.save(new TodoItem(formData.getTitle(), false));
    model.addAttribute("item", toDto(item));
    return "fragments :: todoItem";
  }

  @PutMapping("/{id}/toggle")
  public String toggleSelection(@PathVariable("id") Long id) {
    TodoItem todoItem = repository.findById(id).orElseThrow(
        () -> new TodoItemNotFoundException(id));
    todoItem.setCompleted(!todoItem.isCompleted());
    repository.save(todoItem);
    return "redirect:/todo";
  }

  @PutMapping("/toggle-all")
  public String toggleAll() {
    List<TodoItem> todoItems = repository.findAll();
    for (TodoItem todoItem : todoItems) {
      todoItem.setCompleted(!todoItem.isCompleted());
      repository.save(todoItem);
    }
    return "redirect:/todo";
  }

  @DeleteMapping("/{id}")
  public String deleteTodoItem(@PathVariable("id") Long id) {
    repository.deleteById(id);
    return "redirect:/todo";
  }

  @DeleteMapping("/completed")
  public String deleteCompletedItems() {
    List<TodoItem> items = repository.findAllByCompleted(true);
    for (TodoItem item : items) {
      repository.deleteById(item.getId());
    }
    return "redirect:/todo";
  }

  private TodoItemDto toDto(TodoItem todoItem) {
    return new TodoItemDto(todoItem.getId(), todoItem.getTitle(),
                           todoItem.isCompleted());
  }

  private void addAttributesForIndex(Model model, ListFilter listFilter) {
    model.addAttribute("item", new TodoItemFormData());
    model.addAttribute("filter", listFilter);
    model.addAttribute("todos", getTodoItems(listFilter));
    model.addAttribute("totalNumberOfItems", repository.count());
    model.addAttribute("numberOfActiveItems", getNumberOfActiveItems());
    model.addAttribute("numberOfCompletedItems", getNumberOfCompletedItems());
  }

  private List<TodoItemDto> getTodoItems(ListFilter filter) {
    return switch (filter) {
            case ALL -> convertToDto(repository.findAll());
            case ACTIVE -> convertToDto(repository.findAllByCompleted(false));
            case COMPLETED -> convertToDto(repository.findAllByCompleted(true));
        };
    }

    private List<TodoItemDto> convertToDto(List<TodoItem> todoItems) {
        return todoItems
                .stream()
                .map(todoItem -> new TodoItemDto(todoItem.getId(),
                        todoItem.getTitle(),
                        todoItem.isCompleted()))
                .collect(Collectors.toList());
    }

    private int getNumberOfActiveItems() {
        return repository.countAllByCompleted(false);
    }

    private int getNumberOfCompletedItems() {
        return repository.countAllByCompleted(true);
    }

    public record TodoItemDto(long id, String title, boolean completed) {
    }

    public enum ListFilter {
        ALL,
        ACTIVE,
        COMPLETED
    }
}
