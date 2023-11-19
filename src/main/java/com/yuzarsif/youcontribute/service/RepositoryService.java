package com.yuzarsif.youcontribute.service;

import com.yuzarsif.youcontribute.exception.DuplicatedRepositoryException;
import com.yuzarsif.youcontribute.models.Repository;
import com.yuzarsif.youcontribute.repository.RepositoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryService {

    private final RepositoryRepository repository;

    public RepositoryService(RepositoryRepository repository) {
        this.repository = repository;
    }

    public void create(String organization, String repository) {
        validate(organization, repository);
        Repository r = Repository.builder().organization(organization).repository(repository).build();
        this.repository.save(r);
    }

    public List<Repository> list() {
        return repository.findAll();
    }

    private void validate(String organization, String repository) {
        this.repository.findByOrganizationAndRepository(organization, repository)
                .ifPresent((r) -> {
                    throw new DuplicatedRepositoryException(organization, repository);
                });
    }
}
