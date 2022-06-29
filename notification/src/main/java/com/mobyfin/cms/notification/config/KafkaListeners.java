package com.mobyfin.cms.notification.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private static final String topic = "${topics.notification.name}";
    private static final String groupId = "${topics.notification.group-id}";
    @KafkaListener(topics = topic, groupId = groupId)
    void listener(String data) {
        System.out.println("Listener received: " + data);
    }
}
