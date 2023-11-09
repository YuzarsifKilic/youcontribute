package com.yuzarsif.youcontribute.repository;

import com.yuzarsif.youcontribute.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, String> {
}
