package ru.digitalleague.decorator.translator.languages;

import ru.digitalleague.decorator.enums.NotificationEnum;

import java.util.HashMap;

public class FrenchNotification implements LanguageStandardNotification {
    private HashMap<NotificationEnum, String> frenchNotifications = new HashMap<>();
    {
        frenchNotifications.put(NotificationEnum.OK, "L'analyse du programme n'a révélé aucune erreur. Les choses sont bonnes!");
        frenchNotifications.put(NotificationEnum.WARNING, "L'analyse du fonctionnement du programme a révélé plusieurs points" +
                " dangereux auxquels il faut prêter attention!");
        frenchNotifications.put(NotificationEnum.ERROR, "Une erreur fatale s'est produite dans le programme! " +
                "Le flux de travail a été arrêté!");
    }

    @Override
    public HashMap<NotificationEnum, String> getNotifications() {
        return frenchNotifications;
    }

    @Override
    public void setNotifications(HashMap<NotificationEnum, String> frenchNotifications) {
        this.frenchNotifications = frenchNotifications;
    }
}
