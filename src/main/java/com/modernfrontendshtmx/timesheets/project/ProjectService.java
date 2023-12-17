package com.modernfrontendshtmx.timesheets.project;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
  private final Map<Integer, Project> projects = new HashMap<>();

  public ProjectService() {
    projects.putAll(
        Stream
            .of(new Project(1, "SaaS"), new Project(2, "Blog Platform"),
                new Project(3, "Ecommerce"))
            .collect(Collectors.toMap(Project::id, Function.identity())));
  }

  public List<Project> getProjects() {
    return projects.values()
        .stream()
        .sorted(Comparator.comparing(Project::id))
        .toList();
  }
}
