package com.modernfrontendshtmx.todomvc.web;

import com.modernfrontendshtmx.todomvc.TodoItem;
import com.modernfrontendshtmx.todomvc.TodoItemNotFoundException;
import com.modernfrontendshtmx.todomvc.TodoItemRepository;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxTrigger;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

  @GetMapping("/active-items-count")
  @HxRequest
  public String todoActiveItemsCount(Model model) {
      model.addAttribute("numberOfActiveItems", getNumberOfActiveItems());
      return "fragments :: active-items-count";
  }

  @GetMapping("/completed")
  public String todoCompleted(Model model) {
    addAttributesForIndex(model, ListFilter.COMPLETED);
    return "todo";
  }

  @PostMapping
  @HxRequest
  @HxTrigger("itemAdded")
  public String addNewTodoItem(TodoItemFormData formData, Model model) {
    TodoItem item = repository.save(new TodoItem(formData.getTitle(), false));
    model.addAttribute("item", toDto(item));
    return "fragments :: todoItem";
  }

  @PutMapping("/{id}/toggle")
  @HxRequest
  @HxTrigger("itemCompletionToggled")
  public String toggleSelection(@PathVariable("id") Long id, Model model) {
    TodoItem todoItem = repository.findById(id).orElseThrow(
        () -> new TodoItemNotFoundException(id));
    todoItem.setCompleted(!todoItem.isCompleted());
    repository.save(todoItem);
    model.addAttribute("item", toDto(todoItem));
    return "fragments :: todoItem";
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
  @HxRequest
  @ResponseBody
  @HxTrigger("itemDeleted")
  public String deleteTodoItem(@PathVariable("id") Long id) {
    repository.deleteById(id);
    return "";
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
