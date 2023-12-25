package com.modernfrontendshtmx.githubtree;

import java.io.IOException;
import java.util.List;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;
import org.springframework.stereotype.Component;

@Component
public class GithubGateway {
  public List<String> getRepositories(String username) {
    try {
      GitHub github = GitHub.connectAnonymously();
      GHUser githubUser = github.getUser(username);
      PagedIterable<GHRepository> ghRepositories =
          githubUser.listRepositories();
      return ghRepositories.toList()
          .stream()
          .map(GHRepository::getName)
          .toList();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<RepositoryRelease> getRepositoryReleases(String username,
                                                       String repositoryName) {
    try {
      GitHub github = GitHub.connectAnonymously();
      GHUser neil = github.getUser(username);
      GHRepository repository = neil.getRepository(repositoryName);
      return repository.listReleases()
          .toList()
          .stream()
          .map(ghRelease
               -> new RepositoryRelease(ghRelease.getId(), ghRelease.getName()))
          .toList();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public record RepositoryRelease(long id, String name) {}
}
