package ru.philosophyit.internship.javabase.time.localdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String... args) {

        // Отобразите календарь текущего месяца в консоли
        // например:
        // пн вт ср чт пт сб вс
        // 30 31 1  2  3  4  5
        // и т.д.

        LocalDate localDate = LocalDate.now();
        DayOfWeek currentDayName = localDate.getDayOfWeek();
        int daysFromNowToSunday = DayOfWeek.SUNDAY.ordinal() - currentDayName.ordinal();
        int daysFromMondayToSunday = 6;
        Month currentMonth = localDate.getMonth();
        Month nextMonth = localDate.getMonth().plus(1);

        if (!currentDayName.equals(DayOfWeek.SUNDAY)){
            localDate = localDate.plusDays(daysFromNowToSunday);
        }
        while (currentMonth.equals(localDate.minusWeeks(1).getMonth())) {
            localDate = localDate.minusWeeks(1);
        }
        localDate = localDate.minusDays(daysFromMondayToSunday);

        System.out.print("  пн  вт  ср  чт  пт  сб  вс");
        System.out.println();
        while (!nextMonth.equals(localDate.getMonth())) {
            int i = 1;
            while (i % 8 != 0) {
                if (localDate.getDayOfMonth() < 10) {
                    System.out.print("   " + localDate.getDayOfMonth());
                } else {
                    System.out.print("  " + localDate.getDayOfMonth());
                }
                i++;
                localDate = localDate.plusDays(1);
            }
            System.out.println();
        }
    }
}
