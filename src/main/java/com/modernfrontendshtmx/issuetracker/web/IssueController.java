package com.modernfrontendshtmx.issuetracker.web;

import com.modernfrontendshtmx.issuetracker.Issue;
import com.modernfrontendshtmx.issuetracker.usecase.GetIssueUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/issues")
@AllArgsConstructor
public class IssueController {
  private final GetIssueUseCase getIssueUseCase;

  @GetMapping("/{key}")
  public String showIssue(@PathVariable("key") String key, Model model) {
    Issue issue = getIssueUseCase.execute(key);
    model.addAttribute("issue", issue);
    return "issue";
  }
}
