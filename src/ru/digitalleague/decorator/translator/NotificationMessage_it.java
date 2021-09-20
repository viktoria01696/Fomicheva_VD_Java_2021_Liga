package ru.digitalleague.decorator.translator;

import java.util.ListResourceBundle;

public class NotificationMessage_it extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"OK", "Le cose vanno bene!"},
                {"WARNING", "Avvertenza: qualcosa va storto!"},
                {"ERROR", "Si è verificato un errore fatale!"}
        };
    }
}
