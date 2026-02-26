package com.henry.tryout.leetcodes.Huawei.phase3.sliding_window.longest_repeating_character_replacement_424.exe;

public class Solution_qianwen_sliding_window {
    public int characterReplacement(String capitalCharArr, int maxReplaceTimes) {
        if (capitalCharArr == null ||
                capitalCharArr.length() == 0) {
            return 0;
        }

        // 维护 当前窗口中 各字符的频次
        int[] charToItsFrequencyInCurrWindow = new int[26];
        // 窗口的左边界指针
        int currWindowLeftCursor = 0;
        int currWindowRightCursor = 0;
        // 当前窗口中 最高频次（可能 略大于 真实值，但安全）
        int maxFrequencyInCurrWindow = 0;
        // 最长‘单字符子串’的长度
        int maxSubArrLength = 0;


        // 🔁 遍历当前位置，并 把 当前位置 扩展为 窗口的右边界
        for (int currentCharSpot = 0; currentCharSpot < capitalCharArr.length(); currentCharSpot++) {
            currWindowRightCursor = currentCharSpot;
            /* ① 扩展 当前窗口的右边界 */
            char charOnRightBarSpot = capitalCharArr.charAt(currWindowRightCursor);

            // （扩展后）时时维护 当前窗口中 该字符的频次 与 最大频次
            // 手段：以 该字符 相对于 字符'A'的偏移量 来 表示该字符
            charToItsFrequencyInCurrWindow[charOnRightBarSpot - 'A']++;
            // （统计完 该字符的频率 后）尝试更新 当前窗口中‘频次最高的字符’的频次：只增不减（即使 后续字符 离开窗口）
            maxFrequencyInCurrWindow
                    = Math.max(
                        maxFrequencyInCurrWindow, // 选项1：当前值
                        charToItsFrequencyInCurrWindow[charOnRightBarSpot - 'A']); // 选项2：当前窗口中该字符的出现频次

            /* （得到窗口中的最大频次后）按需维护 ‘窗口的合法性’（nonMaxFrequencyCharAmount < k） */
            // 原理：让窗口 全变成同一字符 所需的 最小替换次数 minRequiredReplaceTimes = (窗口长度) - (窗口内 最高频字符的出现次数)；
            while ((currentCharSpot - currWindowLeftCursor + 1) - maxFrequencyInCurrWindow > maxReplaceTimes) {
                // 手段：如不合法，则 收缩 窗口的左边界，使其合法
                // Ⅰ 更新 左边界字符 在当前窗口中的出现频次
                charToItsFrequencyInCurrWindow[capitalCharArr.charAt(currWindowLeftCursor) - 'A']--;
                // Ⅱ 更新 左边界指针的位置
                currWindowLeftCursor++;
                // ⚠ 注意：这里不更新 maxFrequencyInCurrWindow！原因见下方说明
            }

            /* （得到合法的窗口后）尝试使用 当前合法窗口所能得到的子串的长度 来 更新 全局子串的最大长度 */
            int currentLegitWindowLength = currentCharSpot - currWindowLeftCursor + 1;
            maxSubArrLength = Math.max(maxSubArrLength, currentLegitWindowLength);
        }

        return maxSubArrLength;
    }
}
