package com.yuzarsif.youcontribute.controllers;

import com.yuzarsif.youcontribute.controllers.requests.UpdateChallengeRequest;
import com.yuzarsif.youcontribute.controllers.resources.IssueResource;
import com.yuzarsif.youcontribute.service.IssueChallengeService;
import com.yuzarsif.youcontribute.service.IssueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class IssueChallengeController {

    private final IssueChallengeService issueChallengeService;

    public IssueChallengeController(IssueChallengeService issueChallengeService) {
        this.issueChallengeService = issueChallengeService;
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable("id") Integer id, @RequestBody UpdateChallengeRequest request) {
        issueChallengeService.updateStatus(id, request.getStatus());
    }
}
