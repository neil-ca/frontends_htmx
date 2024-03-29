package com.modernfrontendshtmx.issuetracker.web;

import com.modernfrontendshtmx.issuetracker.Issue;
import com.modernfrontendshtmx.issuetracker.usecase.GetIssueUseCase;
import com.modernfrontendshtmx.issuetracker.usecase.ReorderSubtasksUseCase;
import com.modernfrontendshtmx.issuetracker.usecase.UpdateSummaryUseCase;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/issues")
@AllArgsConstructor
public class IssueController {
  private final GetIssueUseCase getIssueUseCase;
  private final UpdateSummaryUseCase updateSummaryUseCase;
  private final ReorderSubtasksUseCase reorderSubtasksUseCase;

  @GetMapping("/{key}")
  public String showIssue(@PathVariable("key") String key, Model model) {
    Issue issue = getIssueUseCase.execute(key);
    model.addAttribute("issue", issue);
    return "issue";
  }

  @HxRequest
  @GetMapping("/{key}/summary/inline-edit-form")
  public String summaryInlineEditForm(@PathVariable("key") String key,
                                      Model model) {
    Issue issue = getIssueUseCase.execute(key);
    model.addAttribute("issue", issue);
    SummaryUpdateFormData formData = new SummaryUpdateFormData();
    formData.setSummary(issue.getSummary());
    model.addAttribute("formData", formData);
    return "fragments :: issue-summary-edit";
  }

  @HxRequest
  @PutMapping("/{key}/summary")
  public String summaryUpdate(@PathVariable("key") String key,
                              @Valid @ModelAttribute("formData")
                              SummaryUpdateFormData formData,
                              BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      Issue issue = getIssueUseCase.execute(key);
      model.addAttribute("issue", issue);
      return "fragments :: issue-summary-edit";
    }

    Issue issue = updateSummaryUseCase.execute(key, formData.getSummary());
    model.addAttribute("issue", issue);
    return "fragments :: issue-summary-view";
  }

  @HxRequest
  @GetMapping("/{key}/summary")
  public String summaryView(@PathVariable("key") String key, Model model) {
    Issue issue = getIssueUseCase.execute(key);
    model.addAttribute("issue", issue);
    return "fragments :: issue-summary-view";
  }

  @HxRequest
  @PutMapping("/{key}/subtasks")
  public String reorderSubtasks(@PathVariable("key") String key, int[] subTaskOrder,
          Model model) {
    Issue issue = reorderSubtasksUseCase.execute(key, subTaskOrder);
    model.addAttribute("issue", issue);
    return "issue :: subtask-items";
  }
  public static class SummaryUpdateFormData {
    @NotBlank private String summary;

    public String getSummary() { return summary; }

    public void setSummary(String summary) { this.summary = summary; }
  }
}
