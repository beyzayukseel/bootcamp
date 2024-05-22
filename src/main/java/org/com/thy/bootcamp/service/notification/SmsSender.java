package org.com.thy.bootcamp.service.notification;

import org.com.thy.bootcamp.model.NotificationModel;
import org.springframework.stereotype.Component;

@Component
public class SmsSender implements NotificationSender {
    public void sendNotification(NotificationModel message) {
       //mock sms sender for group wallet invite link
    }
}
