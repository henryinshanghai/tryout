package com.henry.tryout.leetcodes.Huawei.phase1.str.attendance_record_551.exe;

public class Solution_qianwen_category {
    public boolean checkRecord(String s) {
        int absentDayAmount = 0;
        int consecutiveLateDayAmount = 0;

        for (char currentDayState : s.toCharArray()) {
            if (currentDayState == 'A') { // Absent
                absentDayAmount++;
                if (absentDayAmount >= 2) {
                    return false; // 缺勤 超限
                }

                consecutiveLateDayAmount = 0; // 'A' 中断连续迟到
            } else if (currentDayState == 'L') { // Late
                consecutiveLateDayAmount++;
                if (consecutiveLateDayAmount >= 3) {
                    return false; // 连续迟到 超限
                }
            } else { // currentDayState == 'P'
                // 出席 会 中断连续迟到，因此 重新置〇
                consecutiveLateDayAmount = 0;
            }
        }

        return true;
    }
}
