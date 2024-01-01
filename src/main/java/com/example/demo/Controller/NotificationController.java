package com.example.demo.Controller;

import com.example.demo.dto.MessageDto;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.management.Notification;
@CrossOrigin(origins = "*")
@Controller
public class NotificationController {
    private SimpMessagingTemplate messagingTemplate;



    // Mapped as /app/application
    @MessageMapping("/application")
    @SendTo("/all/notifications")
    public void send(final MessageDto notification) throws Exception{
        messagingTemplate.convertAndSend(notification);
    }


}
