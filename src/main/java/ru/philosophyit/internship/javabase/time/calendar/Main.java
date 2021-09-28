package ru.philosophyit.internship.javabase.time.calendar;

import java.util.Calendar;

public class Main {
    public static void main(String... args) {

        // Отобразите календарь текущего месяца в консоли
        // например:
        // пн вт ср чт пт сб вс
        // 30 31 1  2  3  4  5
        // и т.д.

        Calendar calendar = Calendar.getInstance();
        int numberOfCurrentMonth = calendar.get(Calendar.MONTH);
        int numberOfNextMonth =
                numberOfCurrentMonth == Calendar.DECEMBER ? Calendar.JANUARY : numberOfCurrentMonth + 1;
        int daysFromSundayToMonday = 6;

        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -daysFromSundayToMonday);
        } else {
            calendar.add(Calendar.DATE, -(calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY));
        }

        System.out.print("  пн  вт  ср  чт  пт  сб  вс");
        System.out.println();
        while (calendar.get(Calendar.MONTH) != numberOfNextMonth) {
            int i = 1;
            while (i % 8 != 0) {
                if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
                    System.out.print("   " + calendar.get(Calendar.DAY_OF_MONTH));
                } else {
                    System.out.print("  " + calendar.get(Calendar.DAY_OF_MONTH));
                }
                i++;
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            System.out.println();
        }
    }
}
