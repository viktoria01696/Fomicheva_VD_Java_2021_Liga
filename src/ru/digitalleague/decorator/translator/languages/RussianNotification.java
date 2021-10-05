package ru.digitalleague.decorator.translator.languages;

import ru.digitalleague.decorator.enums.NotificationEnum;

import java.util.HashMap;

public class RussianNotification implements LanguageStandardNotification {

    private HashMap<NotificationEnum, String> russianNotifications = new HashMap<>();
    {
        russianNotifications.put(NotificationEnum.OK, "Анализ работы программы не выявил никаких ошибок. Все хорошо!");
        russianNotifications.put(NotificationEnum.WARNING, "Анализ работы программы выявил несколько опасных моментов, " +
                "на которые следует обратить внимание!");
        russianNotifications.put(NotificationEnum.ERROR, "В работе программы произошла фатальная ошибка! " +
                "Рабочий процесс был остановлен!");
    }

    @Override
    public HashMap<NotificationEnum, String> getNotifications() {
        return russianNotifications;
    }

    @Override
    public void setNotifications(HashMap<NotificationEnum, String> russianNotifications) {
        this.russianNotifications = russianNotifications;
    }
}
