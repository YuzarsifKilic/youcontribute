package com.yuzarsif.youcontribute.repository;

import com.yuzarsif.youcontribute.models.Issue;
import com.yuzarsif.youcontribute.models.IssueChallenge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(initializers = IssueRepositoryTestIT.Initializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
@Testcontainers
class IssueRepositoryTestIT {

    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7");

    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>(MYSQL_57_IMAGE);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private IssueChallengeRepository issueChallengeRepository;

    @Test
    public void it_should_find_random_issue() throws Exception {
        //given
        Issue issue1 = Issue.builder().id(1).build();
        Issue issue2 = Issue.builder().id(2).build();
        Issue issue3 = Issue.builder().id(3).build();
        Issue issue4 = Issue.builder().id(4).build();
        Issue issue5 = Issue.builder().id(5).build();

        issueRepository.saveAll(Arrays.asList(issue1, issue2, issue3, issue4, issue5));
        testEntityManager.flush();

        IssueChallenge issueChallenge1 = IssueChallenge.builder().id(1).issue(issue1).build();
        IssueChallenge issueChallenge2 = IssueChallenge.builder().id(2).issue(issue2).build();

        issueChallengeRepository.saveAll(Arrays.asList(issueChallenge1, issueChallenge2));
        testEntityManager.flush();

        //when
        Optional<Issue> randomIssue = issueRepository.findRandomIssue();

        //then
        then(randomIssue).isPresent();
        then(randomIssue.get().getId()).isNotIn(2, 5);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("spring.datasource.url=" + mysql.getJdbcUrl(),
                            "spring.datasource.username=" + mysql.getUsername(),
                            "spring.datasource.password=" + mysql.getPassword())
                    .applyTo(applicationContext.getEnvironment());
        }
    }

}