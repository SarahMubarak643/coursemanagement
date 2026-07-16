package com.example.coursemanagement.service;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("[SMS] " + message);
    }
}
