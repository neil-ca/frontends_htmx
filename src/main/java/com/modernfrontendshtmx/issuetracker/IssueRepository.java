package com.modernfrontendshtmx.issuetracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class IssueRepository {
  private final Map<String, Issue> issues = new HashMap<>();

  public IssueRepository() {
    issues.putAll(
        Stream
            .of(new Issue(
                "XXX-123", "Fix the bug", IssueType.BUG, IssuePriority.HIGH,
                "1.0",
                List.of(new SubTask("XXX-124", "Debug the persistance part",
                                    Status.IN_PROGRESS),
                        new SubTask("XXX-125", "Check the connection of DB",
                                    Status.DONE),
                        new SubTask("XXX-126", "Check the environment config",
                                    Status.DONE))))
            .collect(Collectors.toMap(Issue::getKey, Function.identity())));
  }

  public Issue getIssue(String key) { return issues.get(key); }

  public void saveIssue(Issue issue) { issues.put(issue.getKey(), issue); }
}
