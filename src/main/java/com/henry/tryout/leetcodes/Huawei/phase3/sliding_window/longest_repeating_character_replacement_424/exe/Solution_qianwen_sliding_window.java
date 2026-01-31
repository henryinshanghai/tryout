package com.henry.tryout.leetcodes.Huawei.phase3.sliding_window.longest_repeating_character_replacement_424.exe;

public class Solution_qianwen_sliding_window {
    public int characterReplacement(String capitalCharArr, int maxReplaceTimes) {
        if (capitalCharArr == null ||
                capitalCharArr.length() == 0) {
            return 0;
        }

        // 记录窗口内 各字符频次
        int[] charToItsFrequencyInWindow = new int[26];
        // 窗口的左边界指针
        int leftBarCursor = 0;
        int rightBarCursor = 0;
        // 当前窗口中 最高频次（可能 略大于 真实值，但安全）
        int maxFrequencyInWindow = 0;
        // 当前合法窗口的长度（也就是合法字符串的长度）
        int legitSubArrMaxLength = 0;


        // 🔁 遍历当前位置，并 把 当前位置 扩展为 窗口的右边界
        for (int currentCharSpot = 0; currentCharSpot < capitalCharArr.length(); currentCharSpot++) {
            rightBarCursor = currentCharSpot;
            /* 将 当前位置的字符 加入进 当前窗口中 */
            // 更新 该字符 在当前窗口中的频次
            char charOnRightBarSpot = capitalCharArr.charAt(rightBarCursor);
            // 手段：以 该字符 相对于 字符'A'的偏移量 来 表示该字符
            charToItsFrequencyInWindow[charOnRightBarSpot - 'A']++;

            // （统计完 该字符的频率 后）尝试更新 当前窗口中‘频次最高的字符’的频次：只增不减（即使 后续字符 离开窗口）
            maxFrequencyInWindow
                    = Math.max(
                        maxFrequencyInWindow, // 选项1：当前值
                        charToItsFrequencyInWindow[charOnRightBarSpot - 'A']); // 选项2：当前窗口中该字符的出现频次

            /* （得到窗口中的最大频次后）判断 当前窗口 是否仍是 ‘合法窗口’（nonMaxFrequencyCharAmount < k） */
            // 原理：让窗口 全变成同一字符 所需的 最小替换次数 minRequiredReplaceTimes = (窗口长度) - (窗口内 最高频字符的出现次数)；
            while ((currentCharSpot - leftBarCursor + 1) - maxFrequencyInWindow > maxReplaceTimes) {
                // 则：收缩 窗口的左边界，使其合法
                // Ⅰ 更新 左边界字符 在当前窗口中的出现频次
                charToItsFrequencyInWindow[capitalCharArr.charAt(leftBarCursor) - 'A']--;
                // Ⅱ 更新 左边界指针的位置
                leftBarCursor++;
                // ⚠ 注意：这里不更新 maxFrequencyInWindow！原因见下方说明
            }

            /* （得到合法的窗口后）尝试使用 当前合法窗口的长度 来 更新 全局子串的最大长度 */
            int currentLegitWindowLength = currentCharSpot - leftBarCursor + 1;
            legitSubArrMaxLength = Math.max(legitSubArrMaxLength, currentLegitWindowLength);
        }

        return legitSubArrMaxLength;
    }
}
