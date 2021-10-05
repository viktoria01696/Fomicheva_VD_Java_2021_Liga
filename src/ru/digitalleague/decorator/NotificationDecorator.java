package ru.digitalleague.decorator;

import ru.digitalleague.decorator.enums.LanguageEnum;
import ru.digitalleague.decorator.translator.Translator;
import ru.digitalleague.decorator.translator.languages.LanguageStandardNotification;

import java.util.*;
import java.util.stream.Collectors;

public class NotificationDecorator implements Notification {

    private final Notification notification;
    private final Locale currentLocale = Locale.getDefault();
    private final String currentLanguage = currentLocale.getLanguage();
    List<String> availableLanguages = Arrays.stream(LanguageEnum.values()).map(Enum::toString).map(String::toLowerCase)
            .collect(Collectors.toList());
    private final Translator translator = new Translator();

    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }

    @Override
    public String getMessage() {
        return translator.translate(currentLanguage, notification.getMessage());
    }

    @Override
    public void printMessage(){
        if (!availableLanguages.contains(currentLanguage)){
            System.out.println("Our system  supports only Russian, French, Italian and English.\n" +
                    "You will see a message in English below:");
        }
        System.out.println(getMessage());
    }

}
