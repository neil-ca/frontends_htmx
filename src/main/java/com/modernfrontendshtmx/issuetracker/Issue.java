package com.modernfrontendshtmx.issuetracker;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Issue {
  private String key;
  private String summary;
  private IssueType type;
  private IssuePriority priority;
  private String fixVersion;
  private List<SubTask> subTasks;

}
