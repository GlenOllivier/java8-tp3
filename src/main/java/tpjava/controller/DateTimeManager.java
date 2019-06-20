package tpjava.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class DateTimeManager {
    private static List<LocalDate> dates;
    private static List<LocalTime> times;
    private static List<LocalDateTime> dateTimes;
    private static final int NB_ELEMENT = 50;

    static {
        dates = new ArrayList<LocalDate>();
        times = new ArrayList<LocalTime>();
        dateTimes = new ArrayList<LocalDateTime>();
        mockList();
    }

    private static void mockList() {
        Random r = new Random();
        for (int i = 0; i < NB_ELEMENT; i++) {
            dates.add(LocalDate.of(r.nextInt(50) + 1970, r.nextInt(12) + 1,
                    r.nextInt(28) + 1));
            times.add(LocalTime.of(r.nextInt(24), r.nextInt(60),
                    r.nextInt(60)));
            dateTimes.add(LocalDateTime.of(r.nextInt(50) + 1970, r.nextInt(12) + 1,
                    r.nextInt(28) + 1, r.nextInt(24),
                    r.nextInt(60), r.nextInt(60)));
        }
    }

    public static void addDate(int y, int m, int d) {
        dates.add(LocalDate.of(y, m, d));
    }

    public static void addTime(int h, int m, int s) {
        times.add(LocalTime.of(h, m, s));
    }

    public static void addDateTime(int y, int M, int d, int h, int m, int s) {
        dateTimes.add(LocalDateTime.of(y, M, d, h, m, s));
    }

    public static List<LocalDate> getDates() {
        return dates;
    }

    public static List<LocalTime> getTimes() {
        return times;
    }

    public static List<LocalDateTime> getDateTimes() {
        return dateTimes;
    }
}
