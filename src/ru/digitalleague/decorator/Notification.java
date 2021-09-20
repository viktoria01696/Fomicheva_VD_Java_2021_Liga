package ru.digitalleague.decorator;

public interface Notification {

    String getMessageFromSystem();
    String getNotificationType();
    void printMessageFromSystem();
}
