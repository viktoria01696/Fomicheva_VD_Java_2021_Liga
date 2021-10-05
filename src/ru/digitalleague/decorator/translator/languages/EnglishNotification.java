package ru.digitalleague.decorator.translator.languages;

import ru.digitalleague.decorator.enums.NotificationEnum;

import java.util.HashMap;

public class EnglishNotification implements LanguageStandardNotification {

    private HashMap<NotificationEnum, String> englishNotifications = new HashMap<>();
    {
        englishNotifications.put(NotificationEnum.OK, "Analysis of the program did not reveal any errors. Everything is fine!");
        englishNotifications.put(NotificationEnum.WARNING, "Analysis of the program's operation revealed several dangerous points that should be researched!");
        englishNotifications.put(NotificationEnum.ERROR, "A fatal error has occurred in the program! " +
                "The workflow has been stopped!");
    }

    @Override
    public HashMap<NotificationEnum, String> getNotifications() {
        return englishNotifications;
    }

    @Override
    public void setNotifications(HashMap<NotificationEnum, String> englishNotifications) {
        this.englishNotifications = englishNotifications;
    }
}
