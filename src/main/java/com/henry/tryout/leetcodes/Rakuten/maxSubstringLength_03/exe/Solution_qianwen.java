package com.henry.tryout.leetcodes.Rakuten.maxSubstringLength_03.exe;

import java.util.HashMap;
import java.util.Map;

public class Solution_qianwen {
    public int lengthOfLongestSubstring(String charSeq) {
        // 记录 字符 -> 字符在字符串中最近一次出现的位置 的映射
        Map<Character, Integer> charToItsLastOccurrenceSpotMap = new HashMap<>();

        // 子字符串的起始位置指针
        int substringStartSpot = 0;
        int legitSubstringMaxLength = 0;

        // 指针指向 向子字符串末尾 追加的字符
        for (int substringEndSpot = 0; substringEndSpot < charSeq.length(); substringEndSpot++) {

            char appendingChar = charSeq.charAt(substringEndSpot);

            // 如果 所追加的字符 先前出现过，并且 在当前窗口内，说明 当前子字符串已经添加过了 该字符，则：
            Integer itsLastOccurrence = charToItsLastOccurrenceSpotMap.get(appendingChar);
            if (charToItsLastOccurrenceSpotMap.containsKey(appendingChar)
                    && withinSubstring(itsLastOccurrence, substringStartSpot)) {
                // 从子字符串中移除该字符 （以保证 滑动窗口中的子字符串 是合法的） 手段：重置 子字符串的起始位置
                substringStartSpot = itsLastOccurrence + 1;
            }

            // 记录 所追加字符 -> 字符出现的位置 的映射
            charToItsLastOccurrenceSpotMap.put(appendingChar, substringEndSpot);

            // 使用 当前子字符串的长度（必定合法） 来 更新 最长子字符串的长度
            legitSubstringMaxLength = Math.max(legitSubstringMaxLength, substringEndSpot - substringStartSpot + 1);
        }

        return legitSubstringMaxLength;
    }

    /**
     * 判断 指定的位置 是不是 在子字符串中
     * @param givenSpot     指定的位置
     * @param substringStartSpot    子字符串的起始位置
     * @return
     */
    private boolean withinSubstring(Integer givenSpot, int substringStartSpot) {
        return givenSpot >= substringStartSpot;
    }
}
