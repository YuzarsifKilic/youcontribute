package com.yuzarsif.youcontribute.controllers;

import com.yuzarsif.youcontribute.controllers.requests.CreateRepositoryRequest;
import com.yuzarsif.youcontribute.controllers.resources.IssueResource;
import com.yuzarsif.youcontribute.controllers.resources.RepositoryResource;
import com.yuzarsif.youcontribute.service.IssueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<IssueResource> list(@RequestParam("repository_id") Integer repositoryId) {
        return IssueResource.createFor(issueService.list(repositoryId));
    }
}
