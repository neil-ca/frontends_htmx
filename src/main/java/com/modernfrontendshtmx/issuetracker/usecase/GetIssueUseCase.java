package com.modernfrontendshtmx.issuetracker.usecase;

import com.modernfrontendshtmx.issuetracker.Issue;
import com.modernfrontendshtmx.issuetracker.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetIssueUseCase {
  private final IssueRepository repository;

  public Issue execute(String key) { return repository.getIssue(key); }
}
