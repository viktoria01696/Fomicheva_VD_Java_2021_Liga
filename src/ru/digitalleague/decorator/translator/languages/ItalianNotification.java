package ru.digitalleague.decorator.translator.languages;

import ru.digitalleague.decorator.enums.NotificationEnum;

import java.util.HashMap;

public class ItalianNotification implements LanguageStandardNotification {
    private HashMap<NotificationEnum, String> italianNotifications = new HashMap<>();
    {
        italianNotifications.put(NotificationEnum.OK, "L'analisi del programma non ha rivelato errori. Le cose vanno bene!");
        italianNotifications.put(NotificationEnum.WARNING, "L'analisi del funzionamento del programma ha rivelato diversi punti " +
                "pericolosi a cui prestare attenzione!");
        italianNotifications.put(NotificationEnum.ERROR, "Si è verificato un errore fatale nel programma! " +
                "Il flusso di lavoro è stato interrotto!");
    }

    @Override
    public HashMap<NotificationEnum, String> getNotifications() {
        return italianNotifications;
    }

    @Override
    public void setNotifications(HashMap<NotificationEnum, String> italianNotifications) {
        this.italianNotifications = italianNotifications;
    }
}
