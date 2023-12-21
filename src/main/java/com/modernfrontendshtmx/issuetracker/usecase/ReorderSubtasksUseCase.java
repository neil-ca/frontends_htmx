package com.modernfrontendshtmx.issuetracker.usecase;

import com.modernfrontendshtmx.issuetracker.Issue;
import com.modernfrontendshtmx.issuetracker.IssueRepository;
import com.modernfrontendshtmx.issuetracker.SubTask;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReorderSubtasksUseCase {
  private final IssueRepository repository;

  public Issue execute(String key, int[] subTaskOrder) {
    Issue issue = repository.getIssue(key);
    List<SubTask> subTasks = issue.getSubTasks();
    List<SubTask> reordered = new ArrayList<>();
    for (int order : subTaskOrder) {
      reordered.add(subTasks.get(order));
    }
    issue.setSubTasks(reordered);
    repository.saveIssue(issue);
    return issue;
  }
}
