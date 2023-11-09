package com.yuzarsif.youcontribute.managers;

import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.Repository;
import com.yuzarsif.youcontribute.service.GithubClient;
import com.yuzarsif.youcontribute.service.IssueService;
import com.yuzarsif.youcontribute.service.RepositoryService;
import com.yuzarsif.youcontribute.service.models.GithubIssueResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryManager {

    private final RepositoryService repositoryService;
    private final IssueService issueService;
    private final GithubClient githubClient;

    public RepositoryManager(RepositoryService repositoryService, IssueService issueService, GithubClient githubClient) {
        this.repositoryService = repositoryService;
        this.issueService = issueService;
        this.githubClient = githubClient;
    }

    public void importRepository(String organization, String repository) {
        repositoryService.create(organization, repository);
    }

    @Async
    public void importIssues(Repository repository) {
        LocalDate sinceYesterday = LocalDate.ofInstant(Instant.now().minus(1, ChronoUnit.DAYS),
                ZoneId.systemDefault());
        GithubIssueResponse[] githubIssueResponses = githubClient.listIssues(
                repository.getOrganization(), repository.getRepository(), sinceYesterday);

        List<Issue> issues = Arrays.stream(githubIssueResponses)
                .map(githubIssue -> Issue
                        .builder()
                        .title(githubIssue.getTitle())
                        .body(githubIssue.getBody())
                        .build())
                .collect(Collectors.toList());

        issueService.saveAll(issues);
    }
}
