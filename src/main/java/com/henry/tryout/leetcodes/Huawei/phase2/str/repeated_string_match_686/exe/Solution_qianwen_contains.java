package com.henry.tryout.leetcodes.Huawei.phase2.str.repeated_string_match_686.exe;

public class Solution_qianwen_contains {
    public int repeatedStringMatch(String strToRepeat, String strToBeSubstring) {
        int lengthOfStrToRepeat = strToRepeat.length();
        int lengthOfStrToBeSubstring = strToBeSubstring.length();

        /* 计算最小重复次数 k = (len(b) + len(a) - 1) / len(a) */
        // AB   ABA (3 + 2 - 1) / 2 = 4 / 2 = 2次
        int leastRepeatTimes =
                (lengthOfStrToBeSubstring + lengthOfStrToRepeat - 1) / lengthOfStrToRepeat;

        /* 构造重复 leastRepeatTimes 次的字符串 */
        StringBuilder repeatLeastTimesSB = new StringBuilder();
        for (int currentTime = 0; currentTime < leastRepeatTimes; currentTime++) {
            repeatLeastTimesSB.append(strToRepeat);
        }
        String repeatLeastTimesStr = repeatLeastTimesSB.toString();

        /* 查看重复最少次数后，是否使得 b成为子串 */
        // 如果 重复了最少次数所得到的字符串中 包含有 指定的子串，说明 我们重复这么些次就可以了，
        if (repeatLeastTimesStr.contains(strToBeSubstring)) {
            // 则：返回 所重复的次数
            return leastRepeatTimes;
        }

        /* 否则，说明我们需要 再重复一次（以尝试匹配子串）*/
        // ABC  CABCABCA    (8 + 3 - 1) / 3 = 10 / 3 = 3(次)
        // 重复三次的字符串 AB-CABCABC（不包含 目标子串 因为 少了一个字符）
        // 重复4次的字符串 AB-CABCABCA-BC（包含有 目标子串）
        repeatLeastTimesSB.append(strToRepeat);
        // 如果 多重复一次的字符串 包含有 目标子串，说明 我们需要重复 这么些次，则：
        if (repeatLeastTimesSB.toString().contains(strToBeSubstring)) {
            // 返回 具体的次数
            return leastRepeatTimes + 1;
        }

        // 如果 尝试了 重复K次 与 重复K+1次后，都没有得到 预期结果，
        // 说明 无法实现 通过重复字符串A 来 使字符串B成为子串，则：
        // 按照题意 返回-1
        return -1;
    }
}
