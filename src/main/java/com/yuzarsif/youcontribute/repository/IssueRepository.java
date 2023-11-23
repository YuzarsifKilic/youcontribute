package com.yuzarsif.youcontribute.repository;

import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, String> {

    List<Issue> findByRepository(Repository repository);

    @Query(value = "select * from issue where id not in (select issue_id from issue_challenge) order by rand() limit 1", nativeQuery = true)
    Optional<Issue> findRandomIssue();

    Optional<Issue> findByGithubIssuesId(Long id);
}
