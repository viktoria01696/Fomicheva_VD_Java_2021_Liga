package ru.digitalleague.decorator;

public class Main {
    public static void main(String[] args) {
        Notification notification = new NotificationFromSystem(RandomNotification.getNotification());
        Notification translatedNotification = new NotificationDecorator(notification);
        translatedNotification.printMessage();
    }
}
