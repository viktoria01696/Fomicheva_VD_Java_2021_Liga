package ru.digitalleague.decorator.translator;

import java.util.ListResourceBundle;

public class NotificationMessage_ru extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"OK", "Все хорошо!"},
                {"WARNING", "Предупреждение: что-то идет не так!"},
                {"ERROR", "Произошла фатальная ошибка!"}
        };
    }
}
