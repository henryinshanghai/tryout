package com.henry.tryout.leetcodes.Rakuten.maxSubstringLength_03.exe;

// 修改数组元素的值 比起 修改set集合中的元素 要轻量很多
// 但这种方式 有更大的理解成本，跳过~
public class Solution_qianwen_boolean {
    public int lengthOfLongestSubstring(String s) {// 处理空字符串或 null 输入
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // ASCII 字符共 128 个（0～127），用布尔数组标记某字符是否在当前窗口中
        boolean[] isCharInWindow = new boolean[128];

        int windowStart = 0;          // 滑动窗口的起始索引（左边界）
        int maxLength = 0;            // 记录无重复字符子串的最大长度

        // windowEnd 作为右指针，遍历字符串的每个字符
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char currentChar = s.charAt(windowEnd);

            // 如果当前字符已经在窗口中出现过，说明出现了重复
            // 需要收缩窗口左侧，直到该字符不再存在于窗口中
            while (isCharInWindow[currentChar]) {
                char leftmostChar = s.charAt(windowStart);
                isCharInWindow[leftmostChar] = false; // 将左边界字符移出窗口
                windowStart++;                        // 左边界右移
            }

            // 此时窗口 [windowStart, windowEnd] 无重复，将当前字符加入窗口
            isCharInWindow[currentChar] = true;

            // 更新最大长度：当前窗口大小 = windowEnd - windowStart + 1
            int currentWindowLength = windowEnd - windowStart + 1;
            if (currentWindowLength > maxLength) {
                maxLength = currentWindowLength;
            }
        }

        return maxLength;
    }
}
