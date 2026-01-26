package com.henry.tryout.leetcodes.Huawei.phase1.str.dayNo_of_the_year_1154.exe;

public class Solution_qianwen_calculate {
    public int dayOfYear(String date) {
        // Step 1: 解析输入字符串 "YYYY-MM-DD"
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]); // 1-based (1=Jan, 12=Dec)
        int day = Integer.parseInt(parts[2]);

        // Step 2: 定义平年每个月的天数（索引 0 表示 1月，11 表示 12月）
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Step 3: 判断是否为闰年
        // 闰年规则：
        // - 能被 4 整除 且 不能被 100 整除，或者
        // - 能被 400 整除
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        // Step 4: 如果是闰年，二月有 29 天（但我们不修改原数组，而是在累加时处理）
        // 这里我们直接在累加过程中判断：当月份 > 2 且是闰年，则总天数 +1

        // Step 5: 累加前 (month - 1) 个月的天数
        int totalDays = 0;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }

        // Step 6: 加上当前月的天数
        totalDays += day;

        // Step 7: 如果是闰年，并且当前日期在 3月或之后，需要额外加 1 天（因为2月多一天）
        if (isLeapYear && month > 2) {
            totalDays += 1;
        }

        return totalDays;
    }
}
