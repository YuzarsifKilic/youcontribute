package com.yuzarsif.youcontribute.service;

import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.Repository;
import com.yuzarsif.youcontribute.repository.IssueRepository;
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
        issueRepository.saveAll(issues);
    }

    public List<Issue> list(Integer repositoryId) {
        Repository repository = repositoryService.findById(repositoryId);
        return issueRepository.findByRepository(repository);
    }
}
