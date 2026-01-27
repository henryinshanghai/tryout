package com.henry.tryout.leetcodes.Huawei.phase1.str.reformat_phone_number_1694.exe;

// 对末尾的四个字符（按照字符指针的情况）来分类讨论，得到题意要求的字符串
public class Solution_gongshui_specialCases {
    public String reformatNumber(String phoneNumberStr) {
        // 清除掉 原始字符串中的 所有非数字字符
        String pureDigitStr =
                phoneNumberStr
                        .replace(" ", "")
                        .replace("-", "");

        // 构造格式化后的结果字符串
        int digitAmount = pureDigitStr.length();
        StringBuilder resultStr = new StringBuilder();
        for (int currentDigitCursor = 0; currentDigitCursor < digitAmount; currentDigitCursor += 3) {

            /* 特殊情况 */
            // 如果 当前位置 到末尾位置 <=4个字符，说明 剩余字符数量 <=4，则：需要进行特殊处理
            if (currentDigitCursor + 5 > digitAmount) {
                // 如果 剩余字符数量 <=3（剩余2个字符、剩余3个字符），说明 可以直接追加，则：
                if (currentDigitCursor + 3 >= digitAmount) {
                    // 把 剩余字符 追加到 结果字符串中    substring(<start_spot>)用于获取从start_spot到末尾的子串
                    resultStr.append(pureDigitStr.substring(currentDigitCursor));
                } else { // 否则 剩余4个字符，说明 需要把这4个字符 分成两组2字符 添加，则：独立添加两次
                    resultStr.append(pureDigitStr, currentDigitCursor, currentDigitCursor + 2)
                            .append("-").append(pureDigitStr.substring(currentDigitCursor + 2));
                }

                // 特殊处理完成后，不再有字符剩余。退出循环（以防误添加其他字符）
                break;
            }

            /* 一般情况 */
            // 向结果字符串中，添加 数字字符串中 从 当前数字指针所指向的位置（包含） 开始的3个字符
            resultStr.append(pureDigitStr, currentDigitCursor, currentDigitCursor + 3);
            resultStr.append("-");
        }

        return resultStr.toString();
    }
}
