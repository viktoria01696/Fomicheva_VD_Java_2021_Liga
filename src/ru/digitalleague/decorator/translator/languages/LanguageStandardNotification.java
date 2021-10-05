package ru.digitalleague.decorator.translator.languages;

import ru.digitalleague.decorator.enums.NotificationEnum;

import java.util.HashMap;

public interface LanguageStandardNotification {

    HashMap<NotificationEnum, String> getNotifications();
    void setNotifications(HashMap<NotificationEnum, String> notifications);
}
