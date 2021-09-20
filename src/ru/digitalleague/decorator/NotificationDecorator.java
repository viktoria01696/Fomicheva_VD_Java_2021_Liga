package ru.digitalleague.decorator;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class NotificationDecorator implements Notification {

    private final Notification notification;
    Locale currentLocale = Locale.getDefault();
    String currentLanguage = currentLocale.getLanguage();
    String[] availableLanguages = {"ru", "fr", "it"};


    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }

    @Override
    public String getMessageFromSystem() {
        if (Arrays.asList(availableLanguages).contains(currentLanguage)) {
            ResourceBundle translatedNotification = ResourceBundle.getBundle(
                    "ru.digitalleague.decorator.translator.NotificationMessage", currentLocale);
            return translatedNotification.getString(notification.getNotificationType());
        } else {
            return notification.getMessageFromSystem();
        }
    }

    @Override
    public void printMessageFromSystem(){
        if (!Arrays.asList(availableLanguages).contains(currentLanguage) && !currentLanguage.equals("en")){
            System.out.println("Our system  supports only Russian, French, Italian and English.\n" +
                    "You will see a message in English below:");
        }
        System.out.println(getMessageFromSystem());
    }

    @Override
    public String getNotificationType() {
        return notification.getNotificationType() + getMessageFromSystem();
    }
}
