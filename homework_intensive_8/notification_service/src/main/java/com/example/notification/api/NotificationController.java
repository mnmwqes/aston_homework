package com.example.notification.api;

import com.example.notification.mail.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")

public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) { this.emailService = emailService; }

    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestParam String email, @RequestParam String operation) {
        if ("CREATED".equalsIgnoreCase(operation)) {
            emailService.sendAccountCreated(email);
        } else if ("DELETED".equalsIgnoreCase(operation)) {
            emailService.sendAccountDeleted(email);
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
