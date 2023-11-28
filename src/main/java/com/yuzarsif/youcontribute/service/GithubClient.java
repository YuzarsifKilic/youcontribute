package com.yuzarsif.youcontribute.service;

import com.yuzarsif.youcontribute.config.GithubProperties;
import com.yuzarsif.youcontribute.service.models.GithubAccessTokenRequest;
import com.yuzarsif.youcontribute.service.models.GithubAccessTokenResponse;
import com.yuzarsif.youcontribute.service.models.GithubIssueResponse;
import com.yuzarsif.youcontribute.service.models.GithubPullResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Objects;

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

    public GithubPullResponse[] listPullRequests(String owner, String repository) {
        String issuesUrl = String.format("%s/repos/%s/%s/pulls?state=closed",
                githubProperties.getApiUrl(),
                owner,
                repository);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "token " + githubProperties.getToken());
        HttpEntity request = new HttpEntity(httpHeaders);

        ResponseEntity<GithubPullResponse[]> response = restTemplate.exchange(issuesUrl,
                HttpMethod.GET,
                request,
                GithubPullResponse[].class);

        return response.getBody();
    }

    public String getAuthorizeUrl() {
        return String.format("%s?client_id=%s", githubProperties.getAuthorizeUrl(), githubProperties.getClientId());
    }

    public String getAccessToken(String code) {
        GithubAccessTokenRequest accessTokenRequest = GithubAccessTokenRequest
                .builder()
                .clientId(githubProperties.getClientId())
                .clientSecret(githubProperties.getClientSecret())
                .code(code)
                .build();
        HttpEntity<GithubAccessTokenRequest> request = new HttpEntity<>(accessTokenRequest);
        ResponseEntity<GithubAccessTokenResponse> exchange = restTemplate.exchange(githubProperties.getAccessTokenUrl(), HttpMethod.POST, request, GithubAccessTokenResponse.class);
        return Objects.requireNonNull(exchange.getBody()).getAccessToken();
    }
}
