package com.yuzarsif.youcontribute.schedulers;

import com.yuzarsif.youcontribute.clients.OneSignalClient;
import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.IssueChallenge;
import com.yuzarsif.youcontribute.models.IssueChallengeStatus;
import com.yuzarsif.youcontribute.models.Repository;
import com.yuzarsif.youcontribute.service.GithubClient;
import com.yuzarsif.youcontribute.service.IssueChallengeService;
import com.yuzarsif.youcontribute.service.IssueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
@AllArgsConstructor
public class TrackChallengeScheduler {

    private final IssueService issueService;
    private final IssueChallengeService issueChallengeService;
    private final GithubClient githubClient;

    @Scheduled(fixedRateString = "${application.challenge-tracking-frequency}")
    public void challengeIssueScheduler() {
        log.info("Track challenge scheduler started");

        issueChallengeService.list().stream()
                        .filter(issueChallenge -> IssueChallengeStatus.ACCEPTED.equals(issueChallenge.getStatus()))
                                .forEach(issueChallenge -> {
                                    Repository repository = issueChallenge.getIssue().getRepository();
                                    Arrays.stream(this.githubClient
                                            .listPullRequests(repository.getOrganization(), repository.getRepository()))
                                            .filter(pull -> "YuzarsifKilic".equals(pull.getUser().getLogin()) &&
                                                    pull.getBody().contains(String.format("Fixes #%d", issueChallenge.getIssue().getGithubIssueNumber())) &&
                                                    "closed".equals(pull.getState()))
                                            .findFirst()
                                            .ifPresent(pullResponse -> {
                                                this.issueChallengeService.updateStatus(issueChallenge.getId(), IssueChallengeStatus.COMPLETED);
                                            });
                                });

        log.info("Track challenge scheduler finished");
    }
}
