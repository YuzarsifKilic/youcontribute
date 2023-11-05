package com.yuzarsif.youcontribute.controllers;

import com.yuzarsif.youcontribute.controllers.requests.CreateRepositoryRequest;
import com.yuzarsif.youcontribute.controllers.resources.RepositoryResource;
import com.yuzarsif.youcontribute.service.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    private final RepositoryService repositoryService;

    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateRepositoryRequest request) {
        repositoryService.create(request.getOrganization(), request.getRepository());
    }

    @GetMapping
    public List<RepositoryResource> list() {
        return RepositoryResource.createFor(repositoryService.list());
    }
}
