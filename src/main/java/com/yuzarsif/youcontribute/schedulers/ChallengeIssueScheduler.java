package com.yuzarsif.youcontribute.schedulers;

import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.IssueChallenge;
import com.yuzarsif.youcontribute.service.IssueChallengeService;
import com.yuzarsif.youcontribute.service.IssueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ChallengeIssueScheduler {

    private final IssueService issueService;
    private final IssueChallengeService issueChallengeService;

    @Scheduled(fixedRateString = "${application.challenge-frequency}")
    public void challengeIssueScheduler() {
        log.info("Challenge issue scheduler started");
        if (issueChallengeService.hasOnGoingChallenge()) {
            log.warn("There is already an ongoing challenge, skipping...");
            return;
        }

        Issue randomIssue = issueService.findRandomIssue();
        log.info("Found a random issue : {}", randomIssue.getId());
        IssueChallenge issueChallenge = issueChallengeService.create(randomIssue);
        log.info("Challenge issue scheduler finished");
    }
}
