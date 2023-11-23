package com.yuzarsif.youcontribute.clients;

import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import com.yuzarsif.youcontribute.config.ApplicationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class OneSignalClient {

    private final ApplicationProperties applicationProperties;

    public OneSignalClient(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public void sendNotification(Integer challengeId, String issueTitle) {
        NotificationRequest request = new NotificationRequest();
        ApplicationProperties.OneSignalProperties oneSignal = this.applicationProperties.getOneSignal();
        request.setTemplateId(oneSignal.getTemplateId());
        request.setAppId(oneSignal.getAppId());
        request.setAnyWeb(true);
        request.setContents(new HashMap<>() {{
            put("en", String.format("Would you like to solve following challenge?\n%s", issueTitle));
        }});
        Button acceptButton = new Button();
        acceptButton.setId("accept");
        acceptButton.setText("Accept");
        acceptButton.setUrl(String.format("http://localhost:4200/challenge/%d/accept", challengeId));
        Button rejectButton = new Button();
        rejectButton.setId("reject");
        rejectButton.setText("Reject");
        rejectButton.setUrl(String.format("http://localhost:4200/challenge/%d/reject", challengeId));
        request.setWebButtons(Arrays.asList(acceptButton, rejectButton));
        request.setIncludedSegments(new ArrayList<>(){{add("Total Subscriptions");}});
        OneSignal.createNotification(oneSignal.getApiAuthKey(), request);
    }
}
