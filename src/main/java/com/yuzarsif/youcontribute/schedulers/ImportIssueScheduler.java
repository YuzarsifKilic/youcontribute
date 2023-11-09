package com.yuzarsif.youcontribute.schedulers;

import com.yuzarsif.youcontribute.managers.RepositoryManager;
import com.yuzarsif.youcontribute.models.Repository;
import com.yuzarsif.youcontribute.service.RepositoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ImportIssueScheduler {

    private final RepositoryService repositoryService;
    private final RepositoryManager repositoryManager;

    @Scheduled(fixedRateString = "${application.import-frequency}", initialDelay = 60000)
    public void importIssueScheduler() {
        log.info("Import scheduler started");
        List<Repository> repos = repositoryService.list();
        repos.forEach(repositoryManager::importIssues);
        log.info("Import scheduler finished");
    }
}
