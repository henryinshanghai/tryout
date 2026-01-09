package com.henry.tryout.leetcodes.Rakuten.maxSubstringLength_03.exe;

import java.util.HashMap;
import java.util.Map;

// AI改进的点：
// #1 对map映射关系的改进：存储的是 字符 -> 字符最后一次出现的位置
// #2 在使用时(重置窗口的起始位置) 再去+1
public class Solution_huashou_qianwen {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charToItsLastOccurrence = new HashMap<>();
        int windowStart = 0;
        int maxLength = 0;

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char currentAppendingChar = s.charAt(windowEnd);

            if (charToItsLastOccurrence.containsKey(currentAppendingChar)) {
                // #2 跳过重复字符：start 至少为 charToItsLastOccurrence + 1
                /*
                    不需要显式判断 charToItsLastOccurrence 是否在窗口中，是因为：
                        Math.max(lastIndex + 1, windowStart) 会自动忽略那些 lastIndex < windowStart 的“过期”记录。

                    这体现了 滑动窗口算法的一个重要思想：
                        用指针的位置（如 windowStart）来隐式维护窗口状态，避免额外检查。
                 */
                windowStart = Math.max(charToItsLastOccurrence.get(currentAppendingChar) + 1, windowStart);
            }

            // #1 map中 存储的是 真实索引
            charToItsLastOccurrence.put(currentAppendingChar, windowEnd);

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }
}
