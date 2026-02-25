package com.henry.tryout.leetcodes.Huawei.phase2.str.repeated_string_match_686.exe;

public class Solution_qianwen_KMP {
    public int repeatedStringMatch(String strToRepeat, String strToBeSubstring) {
        int repeatStrLength = strToRepeat.length();
        int substringLength = strToBeSubstring.length();

        if (substringLength == 0) return 0;

        // ① 计算最多会尝试匹配的字符数量
        // 先计算 最少所需要的匹配次数
        int leastRepeatTimes =
                (substringLength + repeatStrLength - 1) / repeatStrLength;
        // 再计算 最多会检查的字符数量
        int checkCharMaxAmount =
                (leastRepeatTimes + 1) * repeatStrLength;

        // ② 计算出 模式字符串的 LPS(最长前后缀)数组
        int[] currentSpotToItsPrefixSpot = computeLPS(strToBeSubstring);

        // ③ 准备 文本字符指针、模式字符指针
        int currentTxtCharSpot = 0; // 主串指针（虚拟）
        int currentPatCharSpot = 0; // 模式串指针

        /* ④ 在 文本串 中 尝试匹配 模式串（KMP算法） */
        while (currentTxtCharSpot < checkCharMaxAmount) {
            char currentTxtChar = strToRepeat.charAt(currentTxtCharSpot % repeatStrLength);

            /* Ⅰ 字符失配的情形 */
            // 当 文本字符 与 模式字符 发生了失配时，说明 需要 回退模式字符指针
            while (currentPatCharSpot > 0 &&
                    currentTxtChar != strToBeSubstring.charAt(currentPatCharSpot)) {
                // 则：一直回退 模式字符指针 直到 正确位置 来 继续匹配
                // 回退位置patCursor = lps[patCursor - 1]
                currentPatCharSpot = currentSpotToItsPrefixSpot[currentPatCharSpot - 1];
            }

            /* Ⅱ 字符失配的情况 */
            // 如果 字符匹配，说明 应该继续匹配 下一个位置上的 模式字符 与 文本字符，
            if (currentTxtChar == strToBeSubstring.charAt(currentPatCharSpot)) {
                // 则：把 模式串指针 移动到 下一位置
                currentPatCharSpot++;
            }

            /* Ⅲ 检查是否 完全匹配 */
            // 如果 模式指针 移动到了 模式字符串的末尾，
            if (currentPatCharSpot == substringLength) {
                // 文本字符的数量 / 被重复字符串的字符数量 + 1
                return (currentTxtCharSpot / repeatStrLength) + 1;
            }

            // 把 文本指针 向前前进
            currentTxtCharSpot++;
        }

        return -1;
    }

    // 计算一个模式字符串的LPS
    // 🐖 这个写法（区别于ConstructLPS） 与 KMP算法的写法 非常类似
    private int[] computeLPS(String patternStr) {
        int patCharAmount = patternStr.length();
        int[] currentSpotToItsPrefixLength = new int[patCharAmount];

        // 准备指针
        int currentSubArrEndCursor = 1;
        int currentPrefixEndNextSpotCursor = 0;

        while (currentSubArrEndCursor < patCharAmount) {
            /* 字符失配时的情形 */
            // 当 字符失配 时，连续回退 前缀指针 直到合适位置（或0），然后继续尝试匹配
            while (currentPrefixEndNextSpotCursor > 0 &&
                    patternStr.charAt(currentSubArrEndCursor)
                            != patternStr.charAt(currentPrefixEndNextSpotCursor)) {
                // 回退到的位置：lps[prefixCursor - 1]
                currentPrefixEndNextSpotCursor = currentSpotToItsPrefixLength[currentPrefixEndNextSpotCursor - 1];
            }

            /* 字符匹配时的情形 */
            // 如果 字符匹配，则：
            if (patternStr.charAt(currentSubArrEndCursor)
                    == patternStr.charAt(currentPrefixEndNextSpotCursor)) {
                // 把 前缀指针 向后移动
                currentPrefixEndNextSpotCursor++;
                // 为 lps[i] 进行赋值
                currentSpotToItsPrefixLength[currentSubArrEndCursor] = currentPrefixEndNextSpotCursor;
            }

            /* 不管 前缀指针回退到位置0 或是 字符匹配，子串指针 都要 向后移动一个位置 */
            // （匹配成功 或者 前缀指针回退到位置0）把 子串字符指针 向后移动一个位置（尝试新的匹配起点）
            currentSubArrEndCursor++;
        }

        // 返回所构造的lps[]数组
        return currentSpotToItsPrefixLength;
    }
}
