package com.example.notification.kafka;

import com.example.notification.mail.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private final EmailService emailService;

    public UserEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${app.kafka.topic:user-events}", groupId = "${spring.kafka.consumer.group-id:notification-service}")
    public void onMessage(UserEvent event) {
        if (event == null || event.getEmail() == null) return;
        if (event.getOperation() == UserEvent.Operation.CREATED) {
            emailService.sendAccountCreated(event.getEmail());
        } else if (event.getOperation() == UserEvent.Operation.DELETED) {
            emailService.sendAccountDeleted(event.getEmail());
        }
    }
}
