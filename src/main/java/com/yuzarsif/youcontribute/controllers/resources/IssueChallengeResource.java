package com.yuzarsif.youcontribute.controllers.resources;

import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.IssueChallenge;
import com.yuzarsif.youcontribute.models.IssueChallengeStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class IssueChallengeResource {

    private Integer id;
    private Integer issueId;
    private Integer repositoryId;
    private String repositoryTitle;
    private String issueTitle;
    private Date createdDate;
    private IssueChallengeStatus status;

    public static IssueChallengeResource createFor(IssueChallenge issueChallenge) {
        Issue issue = issueChallenge.getIssue();
        return IssueChallengeResource
                .builder()
                .id(issueChallenge.getId())
                .issueId(issue.getId())
                .repositoryId(issue.getRepository().getId())
                .repositoryTitle(issue.getRepository().getRepository())
                .issueTitle(issue.getTitle())
                .createdDate(issueChallenge.getCreatedAt())
                .status(issueChallenge.getStatus())
                .build();
    }

    public static List<IssueChallengeResource> createFor(List<IssueChallenge> issueChallenges) {
        return issueChallenges.stream()
                .map(IssueChallengeResource::createFor)
                .collect(Collectors.toList());
    }
}
