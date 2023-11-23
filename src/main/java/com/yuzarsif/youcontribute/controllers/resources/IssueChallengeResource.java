package com.yuzarsif.youcontribute.controllers.resources;

import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.Repository;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class IssueResource {

    private Integer id;
    private Long githubIssueId;
    private Integer githubIssueNumber;
    private String title;
    private String body;
    private String url;

    public static IssueResource createFor(Issue issue) {
        return IssueResource
                .builder()
                .id(issue.getId())
                .githubIssueId(issue.getGithubIssuesId())
                .githubIssueNumber(issue.getGithubIssueNumber())
                .title(issue.getTitle())
                .url(issue.getUrl())
                .body(issue.getBody())
                .build();
    }

    public static List<IssueResource> createFor(List<Issue> issues) {
        return issues.stream()
                .map(IssueResource::createFor)
                .collect(Collectors.toList());
    }
}
