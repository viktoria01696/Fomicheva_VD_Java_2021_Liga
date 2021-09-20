package ru.digitalleague.decorator.enums;

public enum NotificationEnum {

    OK("Everything is fine!"),
    WARNING("Warning: something is going wrong!"),
    ERROR("Fatal error occurred!");

    private final String message;

    private NotificationEnum(String message) {
        this.message = message;
    }

    public String getContentOfNotification() {
        return message;
    }
}
