package com.yuzarsif.youcontribute.repository;

import com.yuzarsif.youcontribute.models.IssueChallenge;
import com.yuzarsif.youcontribute.models.IssueChallengeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssueChallengeRepository extends JpaRepository<IssueChallenge, Integer> {

    Optional<IssueChallenge> findByStatusIn(List<IssueChallengeStatus> status);
}
