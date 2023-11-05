package com.yuzarsif.youcontribute.service;

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
        Repository r = Repository.builder().organization(organization).repository(repository).build();
        this.repository.save(r);
    }

    public List<Repository> list() {
        return repository.findAll();
    }
}
