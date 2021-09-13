package ru.philosophyit.internship.javabase.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Main {
    public static void main(String ...args) {

        // Отобразите календарь текущего месяца в консоли
        // например:
        // пн вт ср чт пт сб вс
        // 30 31 1  2  3  4  5
        // и т.д.

        LocalDate localDate = LocalDate.now();
        DayOfWeek currentDayName = localDate.getDayOfWeek();
        Month currentMonth = localDate.getMonth();
        Month nextMonth = localDate.getMonth().plus(1);

        if (currentDayName != DayOfWeek.MONDAY){
           localDate = localDate.minusDays(localDate.getDayOfWeek().ordinal() - DayOfWeek.MONDAY.ordinal());
        }
        while (localDate.getMonth() == currentMonth){
            localDate = localDate.minusWeeks(1);
        }

        System.out.print("  пн  вт  ср  чт  пт  сб  вс");
        System.out.println();
        while (localDate.getMonth() != nextMonth){
            int i = 1;
            while (i % 8 != 0){
                if (localDate.getDayOfMonth() < 10){
                    System.out.print("   "+ localDate.getDayOfMonth());
                }
                else {
                    System.out.print("  "+ localDate.getDayOfMonth());
                }
                i++;
                localDate = localDate.plusDays(1);
            }
            System.out.println();
        }
    }
}
