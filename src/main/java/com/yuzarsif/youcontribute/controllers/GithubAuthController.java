package com.yuzarsif.youcontribute.controllers;

import com.yuzarsif.youcontribute.controllers.requests.GetGithubAccessTokenRequest;
import com.yuzarsif.youcontribute.controllers.resources.GithubAccessTokenResponse;
import com.yuzarsif.youcontribute.service.GithubClient;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/github")
@AllArgsConstructor
public class GithubAuthController {

    private final GithubClient githubClient;

    @GetMapping("/authorize")
    public void authorize(HttpServletResponse response) {
        response.setHeader("Location", githubClient.getAuthorizeUrl());
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
    }

    @PostMapping("/access_token")
    public GithubAccessTokenResponse getAccessToken(@RequestBody GetGithubAccessTokenRequest request) {
        return GithubAccessTokenResponse.builder().accessToken(githubClient.getAccessToken(request.getCode())).build();
    }
}
