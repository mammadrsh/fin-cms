package com.mobyfin.cms.core.notification;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.InvalidTopicException;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaNotification implements NotificationInterface{
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaNotification(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String string) {
        try {
            kafkaTemplate.send("notification-topic", string);
        } catch (InvalidTopicException e) {
            log.info("Error in notification");
        }
    }
}
