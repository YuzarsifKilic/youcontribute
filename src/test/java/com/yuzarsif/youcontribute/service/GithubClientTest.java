package com.yuzarsif.youcontribute.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.yuzarsif.youcontribute.config.GithubProperties;
import com.yuzarsif.youcontribute.service.models.GithubIssueResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes = {GithubClient.class, RestTemplate.class, GithubProperties.class})
@ContextConfiguration(initializers = GithubClientTest.Initializer.class)
@EnableConfigurationProperties(value = GithubProperties.class)
@ExtendWith(SpringExtension.class)
@WireMockTest(httpPort = 8181)
public class GithubClientTest {

    @Autowired
    private GithubClient githubClient;

    @Test
    void it_should_list_issues() throws Exception {
        stubFor(get(urlPathEqualTo("/repos/octocat/Hello-World/issues"))
                .withQueryParam("since", equalTo("2023-09-11"))
                .withHeader("Authorization", equalTo("token ssshhhhhhh"))
                .willReturn(aResponse().withBodyFile("github/issues.json")
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).withStatus(200)
                ));

        GithubIssueResponse[] response = githubClient.listIssues("octocat", "Hello-World", LocalDate.parse("2023-09-11"));

        //then
        then(response).isNotNull();
        then(response.length).isEqualTo(30);
        GithubIssueResponse githubIssueResponse = response[0];
        then(githubIssueResponse.getTitle()).isEqualTo("Jamey");
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            TestPropertyValues.of("github.api-url=" + "http://localhost:8181",
                            "github.token=ssshhhhhhh"
                    )
                    .applyTo(applicationContext.getEnvironment());
        }

    }
}