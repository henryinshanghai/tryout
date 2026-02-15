package com.henry.tryout.leetcodes.Rakuten.maxSubstringLength_03.exe.hashmap;

import java.util.HashMap;
import java.util.Map;

// AI改进的点：
// #1 对map映射关系的改进：存储的是 字符 -> 字符最后一次出现的位置
// #2 在使用时(重置窗口的起始位置) 再去+1
public class Solution_huashou_optimize {
    public int lengthOfLongestSubstring(String s) {
        // 准备一个HashMap  用于记录 字符 -> 该字符（相对于当前位置）最后一次出现的位置
        Map<Character, Integer> charToItsPreviousLastOccurrence = new HashMap<>();
        // 准备一个指针   用于指向 滑动窗口的起始位置
        int currWindowStartCursor = 0;
        // 准备一个int变量    用于维护 当前最长子串的长度
        int maxSubstrLength = 0;

        // 遍历 字符串中的所有字符
        for (int currentCharCursor = 0; currentCharCursor < s.length(); currentCharCursor++) {
            /* ① 扩展当前窗口（向窗口中追加字符） */
            // 手段：以 游标指针的当前位置 作为 当前滑动窗口的结束位置
            int currWindowEndCursor = currentCharCursor;

            char currentAppendingChar = s.charAt(currWindowEndCursor);

            /* ② 追加字符 可能导致 窗口不合法（包含有重复字符），按需调整 起始位置 使窗口合法 */
            // 先 检查‘所追加的字符’ 是否是 重复字符
            // 如果是，说明 可能会需要 更新 滑动窗口的起始位置，
            if (charToItsPreviousLastOccurrence.containsKey(currentAppendingChar)) {
                // 则：按需更新（option1 or option2） 当前窗口的起始位置
                /*  优化👇
                    不需要显式判断 charToItsPreviousLastOccurrence 是否在窗口中，是因为：
                        Math.max(lastIndex + 1, currWindowStartCursor)
                        会自动忽略
                        那些 lastIndex < currWindowStartCursor 的“过期”记录。

                    这体现了 滑动窗口算法的一个重要思想：
                        用指针的位置（如 currWindowStartCursor）来
                        隐式维护 窗口状态，避免 额外检查。
                 */
                currWindowStartCursor = Math.max(
                        charToItsPreviousLastOccurrence.get(currentAppendingChar) + 1, // option1:该字符最近一次出现位置的 下一个位置（最近一次出现的位置 在当前窗口中）
                        currWindowStartCursor); // option2: 无需更新 当前窗口的起始位置（该字符最近一次出现的位置 在当前窗口起始位置之前）
            }

            // 再向map中添加/更新 当前字符的条目
            charToItsPreviousLastOccurrence.put(currentAppendingChar, currWindowEndCursor);

            /* ③ 使用 当前合法窗口的长度 来 尝试更新 最长字串的长度 */
            maxSubstrLength = Math.max(maxSubstrLength, currWindowEndCursor - currWindowStartCursor + 1);
        }
        return maxSubstrLength;
    }
}
