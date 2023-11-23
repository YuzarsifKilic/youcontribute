package com.yuzarsif.youcontribute;

import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import javax.management.Notification;

public class PushNotificationTest {



    @Test
    public void it_should_sent_notification() throws Exception {
        //given
        NotificationRequest request = new NotificationRequest();
        request.setTemplateId("146b3605-6d3f-4a58-8d8b-3e50f08a4219");
        request.setAppId("3ba2ffdc-d019-48f0-8bbf-dd7ea47c1312");
        request.setAnyWeb(true);
        request.setContents(new HashMap<>() {{
            put("base_url", "http://localhost:4200");
            put("issue_title", "Production kubernetes cluster is deleted accidentally!");
            put("challenge_id", "123123");
        }});
        request.setContents(new HashMap<>() {{
            put("en", "fasdfasfasfasdfsafsadf");
        }});
        Button acceptButton = new Button();
        acceptButton.setId("accept");
        acceptButton.setText("Accept");
        acceptButton.setUrl("http://localhost:4200/challenges/5/accept");
        Button rejectButton = new Button();
        rejectButton.setId("reject");
        rejectButton.setText("Reject");
        rejectButton.setUrl("http://localhost:4200/challenges/5/reject");
        request.setWebButtons(Arrays.asList(acceptButton, rejectButton));
        request.setIncludedSegments(new ArrayList<>(){{add("Total Subscriptions");}});
        OneSignal.createNotification("NDEyNTRjODAtMWFiMy00YTBhLWEzN2UtNzc1MzcwZTUxYjIx", request);
        //when

        //then


    }
}
