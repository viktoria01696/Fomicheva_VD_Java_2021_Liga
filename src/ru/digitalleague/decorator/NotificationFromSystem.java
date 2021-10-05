package ru.digitalleague.decorator;

import ru.digitalleague.decorator.enums.NotificationEnum;

public class NotificationFromSystem implements Notification{

    private final NotificationEnum notification;

    public NotificationFromSystem(NotificationEnum notification) {
        this.notification = notification;
    }

    @Override
    public String getMessage() {
        return notification.toString();
    }

    @Override
    public void printMessage() {
        System.out.println(getMessage());
    }
}
