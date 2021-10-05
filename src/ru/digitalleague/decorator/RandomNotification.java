package ru.digitalleague.decorator;

import ru.digitalleague.decorator.enums.NotificationEnum;

public class RandomNotification{

    public static NotificationEnum getNotification(){
        int randomNumberToGetMessage = (int) Math.floor(Math.random() * 3);
        return NotificationEnum.values()[randomNumberToGetMessage];
    }
}
