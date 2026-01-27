package com.henry.tryout.leetcodes.Huawei.phase1.str.reformat_phone_number_1694.exe;

// 先按一般规则追加，再按照题目要求分类处理末尾的几个字符
public class Solution_guanzheng_specialCases {
    public String reformatNumber(String phoneNumberCharSeq) {
        StringBuilder resultStr = new StringBuilder();

        // 记录数字的个数
        int currentDigitsAmount = 0;
        char[] phoneNumberCharArr = phoneNumberCharSeq.toCharArray();
        for (int currentNumCursor = 0; currentNumCursor < phoneNumberCharArr.length; currentNumCursor++) {
            // 如果 当前字符 是 非数字字符，
            if (!Character.isDigit(phoneNumberCharArr[currentNumCursor])) {
                // 则：直接跳过
                continue;
            }
            // 累计 原始手机号码中的 数字字符数量
            currentDigitsAmount++;
            // 把 当前数字字符 添加到 结果字符串中
            resultStr.append(phoneNumberCharArr[currentNumCursor]);
            // 在结果字符串中，每添加三个数字 就紧跟着添加一个破折号
            if (currentDigitsAmount > 0 && currentDigitsAmount % 3 == 0) {
                resultStr.append('-');
            }
        } /* 🐖 这个循环过程 能够处理 剩余 2个数字的情况 */

        /* 处理 特殊情况 */
        // 如果 原始手机号中 数字字符的数量 刚好是3的倍数，说明 上述for循环 多添加了一个 -符号，
        if (currentDigitsAmount % 3 == 0) {
            // 则：去除 末尾多加的‘-’
            resultStr = resultStr.deleteCharAt(resultStr.length() - 1);
        } else if (currentDigitsAmount % 3 == 1) { // 如果 原始手机号中 数字字符的数量 取模3余1，说明会得到 xxx-123-4的结果
            // 则：① 删除 倒数第二个位置上的'-'；② 在正确的位置上 插入一个'-'
            resultStr =
                    resultStr.deleteCharAt(resultStr.length() - 2) // 删除 不想要的'-'
                            .insert(resultStr.length() - 2, '-'); // 在想要的位置上 插入'-'
        }
        return resultStr.toString();

    }
}
