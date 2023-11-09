package com.yuzarsif.youcontribute.service;

import com.yuzarsif.youcontribute.config.GithubProperties;
import com.yuzarsif.youcontribute.service.models.GithubIssueResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class GithubClient {

    private final RestTemplate restTemplate;
    private final GithubProperties githubProperties;

    public GithubClient(RestTemplate restTemplate, GithubProperties githubProperties) {
        this.restTemplate = restTemplate;
        this.githubProperties = githubProperties;
    }

    public GithubIssueResponse[] listIssues(String owner, String repository, LocalDate since) {
        String issuesUrl = String.format("%s/repos/%s/%s/issues?since=%s",
                githubProperties.getApiUrl(),
                owner,
                repository,
                since.toString());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "token " + githubProperties.getToken());
        HttpEntity request = new HttpEntity(httpHeaders);

        ResponseEntity<GithubIssueResponse[]> response = restTemplate.exchange(issuesUrl,
                HttpMethod.GET,
                request,
                GithubIssueResponse[].class);

        return response.getBody();
    }
}
