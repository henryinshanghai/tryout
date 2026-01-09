package com.henry.tryout.leetcodes.Rakuten.maxSubstringLength_03.exe;

import java.util.HashMap;
import java.util.Map;

// 与 标准做法的不同：map 记录的是 字符 -> 字符最近出现位置的下一个位置；
public class Solution_huashoudapeng {
    public int lengthOfLongestSubstring(String s) {
        int charAmount = s.length(), maxLength = 0;
        // 记录 字符 -> 字符最近一次出现的位置的下一个位置(用于子字符串的重开)
        Map<Character, Integer> characterToNextStartSpotMap = new HashMap<>();

        for (int substringEndSpot = 0, substringStartSpot = 0; substringEndSpot < charAmount; substringEndSpot++) {
            char currentAppendingChar = s.charAt(substringEndSpot);

            if (characterToNextStartSpotMap.containsKey(currentAppendingChar)) {
                // 重置 子字符串的起始位置 目的：跳过 先前重复的旧字符，重建子字符串
                // 避免start指针回退(如果 该重复字符 出现 在滑动窗口之前)  手段: max(原始位置, 更新后的位置)
                substringStartSpot = Math.max(characterToNextStartSpotMap.get(currentAppendingChar), substringStartSpot);
            }
            // 计算 当前滑动窗口的位置，并 使用它来更新 最大长度
            maxLength = Math.max(maxLength, substringEndSpot - substringStartSpot + 1);

            // 记录 字符 -> 字符最近出现位置的下一个位置（🐖）；   用于帮助 重置 子字符串的起始位置
            characterToNextStartSpotMap.put(currentAppendingChar, substringEndSpot + 1);
        }
        return maxLength;
    }
}
