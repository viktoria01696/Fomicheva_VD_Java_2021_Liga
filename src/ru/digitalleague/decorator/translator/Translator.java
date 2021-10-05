package ru.digitalleague.decorator.translator;

import ru.digitalleague.decorator.enums.LanguageEnum;
import ru.digitalleague.decorator.enums.NotificationEnum;
import ru.digitalleague.decorator.translator.languages.*;

import java.util.HashMap;

public class Translator {

    private HashMap<LanguageEnum, LanguageStandardNotification> translations = new HashMap<>();
    {
        translations.put(LanguageEnum.RU, new RussianNotification());
        translations.put(LanguageEnum.EN, new EnglishNotification());
        translations.put(LanguageEnum.FR, new FrenchNotification());
        translations.put(LanguageEnum.IT, new ItalianNotification());
    }

    public String translate(String language, String notification){
        LanguageStandardNotification currentLanguage = translations.getOrDefault(
                LanguageEnum.valueOf(language.toUpperCase()), null);
        if ( currentLanguage!= null){
            return currentLanguage.getNotifications().get(NotificationEnum.valueOf(notification));
        }else{
            return translations.get("EN").getNotifications().get(NotificationEnum.valueOf(notification));
        }
    }

    public HashMap<LanguageEnum, LanguageStandardNotification> getTranslations() {
        return translations;
    }

    public void setTranslations(HashMap<LanguageEnum, LanguageStandardNotification> translations) {
        this.translations = translations;
    }
}
