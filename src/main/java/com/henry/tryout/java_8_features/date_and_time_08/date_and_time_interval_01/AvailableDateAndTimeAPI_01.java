package com.henry.tryout.java_8_features.date_and_time_08.date_and_time_interval_01;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class AvailableDateAndTimeAPI_01 {
    public static void main(String[] args) {

        // 创建一个 LocalDate对象， 并读取对象的属性值
        usingLocalDate();

        // 创建一个 Time对象，并读取对象的属性
        usingLocalTime();

        // 合并日期与时间对象
        usingLocalDateTime();

        // 计算机中的日期与时间表示
        usingInstant();

        // 创建 两个时间对象之间的 duration对象
        usingDurationBetween();
        usingPeriodBetween();
    }

    private static void usingPeriodBetween() {
        // 计算两个日期的时间间隔
        Period period_span = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));

        System.out.println("两个日期之间的间隔是： " + period_span.getDays() + "天");

        // 创建一个日期间隔
        Period ten_days = Period.ofDays(10);
        Period three_weeks = Period.ofWeeks(3);
        Period two_years_six_months_and_one_day = Period.of(2, 6, 1);
    }

    private static void usingDurationBetween() {
        LocalTime time1 = LocalTime.of(19, 27, 30);
        LocalTime time2 = LocalTime.of(19, 28, 30);

        // 计算两个时间点之间的间隔 - 手段： Duration.between()
        /*
            特征：
                1 不能把 LocalDateTime 与 Instant同时作为参数 - 它们的设计目的不相同
                2 duration对象的结果是 秒 / 纳秒；
                3 如果需要使用年、月、日的方式，那么应该用 Period类型
         */
        Duration time_span = Duration.between(time1, time2);
        System.out.println("时间间隔是： " + time_span.getSeconds() + "秒钟");

        // 直接创建出一个时间间隔
        Duration three_minutes = Duration.ofMinutes(3);
        Duration three_minutes_2 = Duration.of(3, ChronoUnit.MINUTES);

        System.out.println("3分钟的时长是： " + three_minutes.getSeconds() + "秒");

    }

    private static void usingInstant() {
        // 使用 一个大的整型数据来表示 日期与时间; - 记录从Unix元年开始所经历的秒数
        // 作用：方便机器使用； 特征：1 由 秒 + 纳秒构成； 2 不能够处理其他的时间单位
        Instant instant = Instant.ofEpochSecond(3);
        long seconds = instant.getEpochSecond();

        System.out.println("seconds: " + seconds);

        // 获取当前的时间戳
        Instant now = Instant.now();
        System.out.println("现在是： " + now); // 2022-05-02T11:24:59.565Z
    }

    private static void usingLocalDateTime() {
        // 创建一个日期时间对象
        LocalDateTime date_time_01 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);

        // 合并date 与 time得到一个 datetime对象
        LocalDate date = LocalDate.of(2014, 3, 18);
        LocalTime time = LocalTime.of(13, 45, 20);
        LocalDateTime date_time_02 = LocalDateTime.of(date, time);

        // 在date对象的基础上，添加time对象 得到一个datetime对象
        LocalDateTime date_time_03 = date.atTime(13, 45, 20);
        LocalDateTime date_time_04 = date.atTime(time);

        // 在time对象的基础上，添加date对象 得到一个datetime对象
        LocalDateTime date_time_05 = time.atDate(date);

        // 从 datetime对象中 获取到date对象/time对象
        LocalDate date_01 = date_time_01.toLocalDate();
        LocalTime time_01 = date_time_01.toLocalTime();
    }

    private static void usingLocalTime() {
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        System.out.println("hour: " + hour + ", minute: " + minute + ", second: " + second);

        // 使用字符串 来 创建时间对象
        LocalTime the_time = LocalTime.parse("13:45:20");
    }

    private static void usingLocalDate() {
        /*
            特征：
                1 不可变对象；
                2 只提供日期信息；
         */
        LocalDate date = LocalDate.of(2014, 3, 18);

        // 使用 字符串来创建Date对象
        LocalDate the_date = LocalDate.parse("2014-03-18");

        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();

        // 2014 MARCH 18
        System.out.println("year: " + year + " Month: " + month + " day: " + day);

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("现在是周几？" + dayOfWeek);
        int dayAmount = date.lengthOfMonth();
        System.out.println("当前月份有 " + dayAmount + "天");
        boolean isLeapYear = date.isLeapYear();
        System.out.println("当前年份是不是闰年？" + isLeapYear);

        LocalDate now = LocalDate.now(); // 当前日期
        System.out.println("现在是： " + now);

        // 传入枚举值作为参数
        int the_year = date.get(ChronoField.YEAR);
        int the_month = date.get(ChronoField.MONTH_OF_YEAR);
        int the_day = date.get(ChronoField.DAY_OF_MONTH);

        System.out.println("the_year: " + the_year + ", the_month: " + the_month + ", the_day: " + the_day);
    }
}
