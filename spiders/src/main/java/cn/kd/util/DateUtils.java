package cn.kd.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate yesterday() {
        LocalDate now = LocalDate.now();
        return now.minusDays(1);
    }

    public static boolean isSameYear() {
        LocalDate yesterday = yesterday();
        LocalDate now = LocalDate.now();
        return yesterday.getYear() == now.getYear();
    }

    public static String yesterdayBeginTime() {
        return LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00"));
    }

    public static String yesterdayEndTime() {
        return LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59"));
    }

    public static void main(String[] args) {
        System.out.println(yesterdayBeginTime());
        System.out.println(yesterdayEndTime());
    }

}
