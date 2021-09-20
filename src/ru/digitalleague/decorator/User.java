package ru.digitalleague.decorator;

public class User {
    public static void main(String[] args) {
        Notification notification = new NotificationFromSystem(NotificationSender.sendNotification());
        Notification translatedNotification = new NotificationDecorator(notification);
        translatedNotification.printMessageFromSystem();
    }

}
