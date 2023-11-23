package com.yuzarsif.youcontribute.service;

import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.Repository;
import com.yuzarsif.youcontribute.repository.IssueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final RepositoryService repositoryService;

    public IssueService(IssueRepository issueRepository, RepositoryService repositoryService) {
        this.issueRepository = issueRepository;
        this.repositoryService = repositoryService;
    }

    @Transactional
    public void saveAll(List<Issue> issues) {
        issues.forEach(issue -> {
            if (issueRepository.findByGithubIssuesId(issue.getGithubIssuesId()).isEmpty()) {
                this.issueRepository.save(issue);
            }
        });
    }

    public List<Issue> list(Integer repositoryId) {
        Repository repository = repositoryService.findById(repositoryId);
        return issueRepository.findByRepository(repository);
    }

    public Issue findRandomIssue() {
        return issueRepository.findRandomIssue()
                .orElseThrow(() -> new EntityNotFoundException("No issues found."));
    }
}
