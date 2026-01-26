package com.henry.tryout.leetcodes.Huawei.phase1.str.buddy_strs_859.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_category {
    public boolean buddyStrings(String sourceStr, String goalStr) {

        // 步骤1：长度不同，直接返回 false
        if (sourceStr.length() != goalStr.length()) {
            return false;
        }

        // 步骤2：记录 所有不同的位置
        List<Integer> diffCharSpots = new ArrayList<>();
        for (int currentSpot = 0; currentSpot < sourceStr.length(); currentSpot++) {
            if (sourceStr.charAt(currentSpot) != goalStr.charAt(currentSpot)) {
                diffCharSpots.add(currentSpot);
            }
        }

        // 步骤3：根据 差异数量 执行不同的分支逻辑
        if (diffCharSpots.size() == 0) {
            // 情况B：s 和 goal 完全相同
            // 需要 检查 s 中 是否有 重复字符（这样才能 交换 两个相同字符）
            int[] charToItsFrequency = new int[26];
            for (char currentChar : sourceStr.toCharArray()) {
                /* 对于 当前字符，查看 它在 字符->频率映射表中 是否已经存在 */
                // 🐖 使用 该字符 相对于'a'的偏移量 来 作为 数组的下标

                // 如果 该字符 已经存在了，说明 当前字符 已经是 多次出现了，则：
                if (charToItsFrequency[currentChar - 'a'] > 0) {
                    // 可以 通过 交换重复字符 来 得到goal，返回true
                    return true;
                }

                // 累计 字符的出现频率
                charToItsFrequency[currentChar - 'a']++;
            }

            // 如果 循环结束 仍旧没有return，说明 源字符串中 不存在 重复字符，
            // 则：无法通过交换字符 得到goal，返回 false
            return false;
        } else if (diffCharSpots.size() == 2) { // 如果 刚好有两个位置 字符不相同，说明 可能是 亲密字符，则：
            // 先得到 这两个具体的位置
            int firstDiffCharSpot = diffCharSpots.get(0),
                    secondDiffCharSpot = diffCharSpots.get(1);

            // 检查是否 交叉相等
            return sourceStr.charAt(firstDiffCharSpot) == goalStr.charAt(secondDiffCharSpot) &&
                    sourceStr.charAt(secondDiffCharSpot) == goalStr.charAt(firstDiffCharSpot);
        } else { // 如果 存在字符差异的位置 不是 0 或者 2，说明 不可能通过 简单交换两个位置上的字符 得到 目标字符串，
            // 则：返回false
            return false;
        }
    }
}
