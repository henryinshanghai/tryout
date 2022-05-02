package com.henry.tryout.java_8_features.date_and_time_08.manipulate_parse_and_format_date_02.modify_date_01;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class ModifyDateInAdvancedWayDemo_02 {
    public static void main(String[] args) {
        // 使用预定义的 TemporalAdjuster 来完成修改
        usingTemporalAdjuster();

        // 使用自定义的TemporalAdjuster 来修改日期
        usingCustomTemporalAdjuster();
    }

    // 静态方法中不能引用 非静态方法
    private static void usingCustomTemporalAdjuster() {
        LocalDate current_date = LocalDate.of(2022, 5, 2);

        // 计算明天的日期 - 跳过休息日
        // 手段1：在with()方法中传入 自定义的 TemporalAdjuster类型
        LocalDate next_work_day = current_date.with(temporal -> {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

            int dayToAdd = 1;
            if (dayOfWeek == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dayOfWeek == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        System.out.println("下一个工作日： " + next_work_day);

        // 手段2：使用 TemporalAdjusters类的静态工厂方法 ofDateAdjuster()
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

                    int dayToAdd = 1;
                    if (dayOfWeek == DayOfWeek.FRIDAY) dayToAdd = 3;
                    else if (dayOfWeek == DayOfWeek.SATURDAY) dayToAdd = 2;
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                }
        );

        LocalDate nextWorkingDaysDate = current_date.with(nextWorkingDay);
        System.out.println("下一个工作日的日期是： " + nextWorkingDaysDate);
    }

    private static void usingTemporalAdjuster() {
        LocalDate date_01 = LocalDate.of(2014, 3, 18);
        // 当周或者下周的周日
        LocalDate date_02 = date_01.with(nextOrSame(DayOfWeek.SUNDAY)); // 2014-03-23

        // 本月的最后一天
        LocalDate date_03 = date_02.with(lastDayOfMonth()); // 2014-03-31
    }
}
