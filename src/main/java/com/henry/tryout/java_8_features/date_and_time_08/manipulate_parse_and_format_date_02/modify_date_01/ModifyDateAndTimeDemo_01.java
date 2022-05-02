package com.henry.tryout.java_8_features.date_and_time_08.manipulate_parse_and_format_date_02.modify_date_01;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class ModifyDateAndTimeDemo_01 {
    public static void main(String[] args) {
        modifyLocalDate();
    }

    private static void modifyLocalDate() {


        // 修改日期的某一个属性 - 手段：withXxx()方法
        // 特征： 不会修改原对象，而是会得到一个新的对象
        updateOneFieldInWithMethod();

        // 在原始日期上 添加或者减掉一段时间
        LocalDate date_01 = LocalDate.of(2014, 3, 18);
        LocalDate date_02 = date_01.plusWeeks(1); // 2014-03-25
        LocalDate date_03 = date_02.minusYears(3); // 2011-03-25
        date_03.plus(6, ChronoUnit.MONTHS); // 2011-09-25
    }

    private static void updateOneFieldInWithMethod() {
        LocalDate date_01 = LocalDate.of(2014, 3, 18);
        LocalDate date_02 = date_01.withYear(2011); // 2011-03-18
        LocalDate date_03 = date_02.withDayOfMonth(25); // 2011-03-25
        LocalDate date_04 = date_03.with(ChronoField.MONTH_OF_YEAR, 9); // 2011-09-25
    }
}
