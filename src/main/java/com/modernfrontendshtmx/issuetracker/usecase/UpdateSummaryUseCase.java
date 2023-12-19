package com.modernfrontendshtmx.issuetracker.usecase;

import com.modernfrontendshtmx.issuetracker.Issue;
import com.modernfrontendshtmx.issuetracker.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateSummaryUseCase {
  private final IssueRepository repository;

  public Issue execute(String key, String summary) {
    Issue issue = repository.getIssue(key);
    issue.setSummary(summary);
    repository.saveIssue(issue);
    return issue;
  }
}
