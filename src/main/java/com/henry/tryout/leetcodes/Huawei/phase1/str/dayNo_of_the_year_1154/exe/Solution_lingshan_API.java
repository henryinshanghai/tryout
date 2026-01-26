package com.henry.tryout.leetcodes.Huawei.phase1.str.dayNo_of_the_year_1154.exe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Solution_lingshan_API {
    // 定义 指定的日期格式
    // 手段：DateTimeFormatter类的静态方法 ofPattern('<format_str>')
    private final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public int dayOfYear(String date) {
        // ① 把 给定的日期字符串 解析成 指定格式的 LocalDate对象
        // 手段：LocalDate的静态方法 parse(<date_str>, <format_obj>)
        return LocalDate.parse(date, formatter)
                .getDayOfYear(); // ② 使用 LocalDate对象的API 来 得到 该日期在当前的天数  -   手段：localDate.getDayOfYear();
    }
}
