package ru.digitalleague.decorator;

import ru.digitalleague.decorator.enums.NotificationEnum;

public class NotificationSender {

    public static NotificationEnum sendNotification(){
        int randomNumberToGetMessage = (int) Math.floor(Math.random() * 3);
        return NotificationEnum.values()[randomNumberToGetMessage];
    }
}
