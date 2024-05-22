package org.com.thy.bootcamp.service.notification;

import org.com.thy.bootcamp.model.NotificationModel;

public interface NotificationSender {
    void sendNotification(NotificationModel message);
}