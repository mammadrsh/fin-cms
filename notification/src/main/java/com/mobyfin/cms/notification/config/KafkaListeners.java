package com.mobyfin.cms.notification.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "notification", groupId = "mobyfin")
    void listener(String data) {
        System.out.println("Listener received: " + data);
    }
}
