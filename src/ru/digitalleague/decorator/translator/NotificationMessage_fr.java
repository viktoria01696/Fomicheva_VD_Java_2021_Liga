package ru.digitalleague.decorator.translator;

import java.util.ListResourceBundle;

public class NotificationMessage_fr extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"OK", "Les choses sont bonnes!"},
                {"WARNING", "Avertissement: quelque chose ne va pas!"},
                {"ERROR", "Une erreur fatale s'est produite!"}
        };
    }
}
