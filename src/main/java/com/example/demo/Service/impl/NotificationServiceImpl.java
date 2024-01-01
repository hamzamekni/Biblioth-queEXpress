package com.example.demo.Service.impl;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Your method to send a message
    public void sendMessage(String destination, String message) {
        messagingTemplate.convertAndSend(destination, message);
    }

}
