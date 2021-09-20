package ru.digitalleague.decorator;

import ru.digitalleague.decorator.enums.NotificationEnum;

public class NotificationFromSystem implements Notification {

    //по умолчанию уведомления отправляются только на английском языке
    NotificationEnum notification;

    public NotificationFromSystem(NotificationEnum notification) {
        this.notification = notification;
    }

    @Override
    public String getMessageFromSystem() {
        return notification.getContentOfNotification();
    }

    @Override
    public String getNotificationType() {
        return notification.toString();
    }

    @Override
    public void printMessageFromSystem() {
        System.out.println(notification.getContentOfNotification());
    }
}
