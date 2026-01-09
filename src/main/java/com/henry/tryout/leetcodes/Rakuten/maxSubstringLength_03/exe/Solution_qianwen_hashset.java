package com.henry.tryout.leetcodes.Rakuten.maxSubstringLength_03.exe;

import java.util.HashSet;
import java.util.Set;

// 为了维护当前窗口中的字符，需要调用 set的remove(char)与add(char)方法
public class Solution_qianwen_hashset {
    public int lengthOfLongestSubstring(String s) {
        // 边界情况：空字符串
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> charsInCurrentWindow = new HashSet<>(); // 记录当前窗口 [substringLeftBar, right] 中的字符
        int substringLeftBar = 0; // 滑动窗口左边界
        int maxLength = 0; // 记录最长无重复子串的长度

        // right 作为右指针，遍历整个字符串
        for (int substringRightBar = 0; substringRightBar < s.length(); substringRightBar++) {
            char appendingChar = s.charAt(substringRightBar);

            // 如果当前字符 appendingChar 已经在窗口中 → 出现重复！
            // 需要不断收缩左边界，直到窗口中不再包含 appendingChar
            while (charsInCurrentWindow.contains(appendingChar)) {
                charsInCurrentWindow.remove(s.charAt(substringLeftBar)); // 移除左边界的字符
                substringLeftBar++; // 左指针右移
            }

            // 此时窗口 [substringLeftBar, substringRightBar] 无重复，加入当前字符
            charsInCurrentWindow.add(appendingChar);

            // 更新最大长度（窗口大小 = substringRightBar - substringLeftBar + 1）
            maxLength = Math.max(maxLength, substringRightBar - substringLeftBar + 1);
        }

        return maxLength;
    }
}
