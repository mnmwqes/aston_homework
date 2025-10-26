package com.example.notification.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) { this.mailSender = mailSender; }

    public void sendAccountCreated(String to) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Аккаунт создан");
        msg.setText("Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан.");
        mailSender.send(msg);
    }

    public void sendAccountDeleted(String to) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Аккаунт удалён");
        msg.setText("Здравствуйте! Ваш аккаунт был удалён.");
        mailSender.send(msg);
    }
}
