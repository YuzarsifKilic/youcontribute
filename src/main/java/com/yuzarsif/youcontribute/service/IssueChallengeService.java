package com.yuzarsif.youcontribute.service;

import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.IssueChallenge;
import com.yuzarsif.youcontribute.models.IssueChallengeStatus;
import com.yuzarsif.youcontribute.repository.IssueChallengeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class IssueChallengeService {

    private final IssueChallengeRepository repository;

    @Transactional
    public IssueChallenge create(Issue issue) {
        IssueChallenge challenge = IssueChallenge
                .builder()
                .issue(issue)
                .status(IssueChallengeStatus.PENDING)
                .build();

        return repository.save(challenge);
    }

    public Boolean hasOnGoingChallenge() {
        return repository.findByStatusIn(IssueChallengeStatus.ongoingStatus()).isPresent();
    }

    public void updateStatus(Integer id, IssueChallengeStatus status) {
        IssueChallenge issueChallenge = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Issue Challenge " + id + " not found"));
        issueChallenge.setStatus(status);
        repository.save(issueChallenge);
    }

    public List<IssueChallenge> list() {
        return repository.findAll();
    }
}
