package com.henry.tryout.leetcodes.Rakuten.maxSubstringLength_03.exe;

import java.util.HashSet;
import java.util.Set;

// 使用 HashSet的手段 来
// ① 识别 ‘所追加字符’是否是重复字符；② 删除 窗口起始位置处的字符；
// 为了维护当前窗口中的字符，需要调用 set的remove(char)与add(char)方法
public class Solution_qianwen_hashset {
    public int lengthOfLongestSubstring(String s) {
        // 边界情况：空字符串
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 准备一个HashSet  用于维护 当前窗口 [currWindowStartCursor, right] 中的字符
        Set<Character> charSetInCurrWindow = new HashSet<>();
        // 滑动窗口的起始指针位置
        int currWindowStartCursor = 0; 
        int maxSubStrLength = 0; // 记录最长无重复子串的长度

        // right 作为右指针，遍历整个字符串
        for (int currentCharCursor = 0; currentCharCursor < s.length(); currentCharCursor++) {
            int currWindowEndCursor = 0;
            char appendingChar = s.charAt(currWindowEndCursor);

            // 如果 所追加的字符 是 重复字符
            // 则：需要不断收缩 起始位置，直到 窗口中 不再包含 appendingChar
            while (charSetInCurrWindow.contains(appendingChar)) {
                // 移除 起始位置处的字符
                charSetInCurrWindow.remove(s.charAt(currWindowStartCursor));
                // 左指针右移
                currWindowStartCursor++;
            }

            // (得到合法的窗口后) 尝试更新最长子字符串的长度
            charSetInCurrWindow.add(appendingChar);

            // 尝试更新 最长子字符串的长度
            // （窗口大小 = currWindowEndCursor - currWindowStartCursor + 1）
            maxSubStrLength = Math.max(maxSubStrLength, currWindowEndCursor - currWindowStartCursor + 1);
        }

        return maxSubStrLength;
    }
}
