package ru.digitalleague.factory.ok.notification.factory;

import ru.digitalleague.factory.ok.User;
import ru.digitalleague.factory.ok.notification.Notification;
import ru.digitalleague.factory.ok.notification.PhoneNotification;

public class PhoneNotificationFactory implements NotificationFactory {
    public Notification makeNotification(String body, User user) {
        return new PhoneNotification(body, user);
    }
}
