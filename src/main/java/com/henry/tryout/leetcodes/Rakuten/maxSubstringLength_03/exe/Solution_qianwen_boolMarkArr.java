package com.henry.tryout.leetcodes.Rakuten.maxSubstringLength_03.exe;

// 使用 布尔标记数组的手段 来
// ① 识别 ‘所追加字符’是否是重复字符；② 删除 窗口起始位置处的字符；
// 🐖 修改数组元素的值 比起 修改set集合中的元素 要轻量很多
// 但这种方式 有更大的理解成本
public class Solution_qianwen_boolMarkArr {
    public int lengthOfLongestSubstring(String s) {
        // 处理 空字符串 或 null 输入
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // 准备一个boolean类型的数组  用于维护当前窗口中的字符
        // 手段：字符 -> 字符是否存在于当前窗口中
        // 原理：ASCII 字符共 128 个（0～127）
        boolean[] charToIfExistInCurrWindow = new boolean[128];

        // 滑动窗口的起始索引（左边界）
        int currWindowStartCursor = 0;          
        // 维护 无重复字符子串的 最大长度
        int maxSubStrLength = 0;            

        // windowEnd 作为右指针，遍历字符串的每个字符
        for (int currentCharCursor = 0; currentCharCursor < s.length(); currentCharCursor++) {
            int currWindowEndCursor = currentCharCursor;
            char currentChar = s.charAt(currWindowEndCursor);

            // 如果 所追加的字符 是重复字符（当前字符 已经在窗口中已存在）
            // 需要 收缩窗口左侧，直到 该字符 不再存在于当前窗口中
            while (charToIfExistInCurrWindow[currentChar]) {
                // 将 起始位置处的字符 移出窗口
                char leftmostChar = s.charAt(currWindowStartCursor);
                charToIfExistInCurrWindow[leftmostChar] = false;
                // 把起始位置指针 向后移动一个位置
                currWindowStartCursor++;
            }

            // 向窗口中追加字符
            charToIfExistInCurrWindow[currentChar] = true;

            // (得到合法的窗口后) 尝试更新最长子字符串的长度
            int currentWindowLength = currWindowEndCursor - currWindowStartCursor + 1;
            if (currentWindowLength > maxSubStrLength) {
                maxSubStrLength = currentWindowLength;
            }
        }

        return maxSubStrLength;
    }
}
