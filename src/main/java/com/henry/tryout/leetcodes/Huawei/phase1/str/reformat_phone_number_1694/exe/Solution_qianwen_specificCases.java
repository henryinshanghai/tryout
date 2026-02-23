package com.henry.tryout.leetcodes.Huawei.phase1.str.reformat_phone_number_1694.exe;

public class Solution_qianwen_specificCases {
    public String reformatNumber(String number) {
        String pureDigitStr = number.replace("-", "").replace(" ", "");
        StringBuilder resultStr = new StringBuilder();

        int currentDigitCursor = 0;
        int digitAmount = pureDigitStr.length();

        /* 每 3 个数字一组，直到剩余 4 个或更少 */
        while (currentDigitCursor > digitAmount - 4) {
            resultStr.append(pureDigitStr, currentDigitCursor, currentDigitCursor + 3);
            resultStr.append("-");
            currentDigitCursor += 3;
        }

        /* 处理剩余数字 所有可能的情况 */
        // ① 剩余 4个数字
        if (digitAmount - currentDigitCursor == 4) {
            // 4 个数字：分成 两组添加（2+2）
            resultStr.append(pureDigitStr, currentDigitCursor, currentDigitCursor + 2);
            resultStr.append("-");
            resultStr.append(pureDigitStr, currentDigitCursor + 2, currentDigitCursor + 4);
        } else {
            // ② 剩余2 或 3 个数字：直接添加
            resultStr.append(pureDigitStr, currentDigitCursor, digitAmount);
        }

        return resultStr.toString();
    }
}
