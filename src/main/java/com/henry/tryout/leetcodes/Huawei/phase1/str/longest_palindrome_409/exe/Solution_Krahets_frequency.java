package com.henry.tryout.leetcodes.Huawei.phase1.str.longest_palindrome_409.exe;

import java.util.HashMap;
import java.util.Map;

public class Solution_Krahets_frequency {
    public int longestPalindrome(String charSeq) {
        // 统计各字符的数量
        HashMap<Character, Integer> characterToItsFrequency = new HashMap<>();

        for (int currentCharSpot = 0; currentCharSpot < charSeq.length(); currentCharSpot++) {
            char currentChar = charSeq.charAt(currentCharSpot);

            /*
                merge(key, value, remappingFunction)
                作用：
                    如果 key 不存在（或对应 value 为 null），则 插入 (key, value)；
                    如果 key 已存在，则 使用 remappingFunction 将 旧值 和 新值 合并，用 结果 来 更新 该key 的值
             */
            characterToItsFrequency.merge(currentChar, 1, (a, b) -> a + b);
        }


        // 统计 构造回文串的最大长度
        int longestPalinLength = 0, middleSpotCharLength = 0;
        for (Map.Entry<Character, Integer> currentCharToItsFrequency : characterToItsFrequency.entrySet()) {
            // 将 当前字符的出现次数 “向下取偶数”，并 计入 longestPalinLength
            int currentCharsFrequency = currentCharToItsFrequency.getValue();

            int reminderAfterDividing2 = currentCharsFrequency % 2;
            int maxAvailableTimes = currentCharsFrequency - reminderAfterDividing2;
            longestPalinLength += maxAvailableTimes;

            // 若 某个字符 出现次数 为 奇数，说明 可以在回文字符串中心位置 添加一个该字符，则：
            if (reminderAfterDividing2 == 1) {
                //  将 middleSpotCharLength 置 1
                middleSpotCharLength = 1;
            }
        }

        // 最终所构造的回文字符串的长度 = 累计的对称字符串长度 + 1（中心位置的长度）
        return longestPalinLength + middleSpotCharLength;
    }
}
