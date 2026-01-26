package com.henry.tryout.leetcodes.Huawei.phase1.str.longest_palindrome_409.exe;

public class Solution_qianwen_frequency {

    public int longestPalindrome(String s) {
        // 计算出 字符的频率数组
        int[] charToItsFrequency = new int[128];

        // 字符串 转 字符数组   手段：s.toCharArray();
        for (char currentChar : s.toCharArray()) {
            charToItsFrequency[currentChar]++;
        }

        int longestPalindromeLength = 0;
        boolean oddFrequencyExist = false;

        // 对于每个字符，把 它可用的最大次数 累计到 最长回文字符串中
        for (int currentFrequency : charToItsFrequency) {

            if (currentFrequency > 0) {
                // 使用 该字符的 最大偶数个（如 5 → 用 4 个）??
                int maxAvailableTimes = (currentFrequency / 2) * 2;
                longestPalindromeLength += maxAvailableTimes;

                // 如果 存在 出现了奇数次的字符，标记 存在性（这段代码 可能会 被多余重复执行多次）
                if (currentFrequency % 2 == 1) {
                    oddFrequencyExist = true;
                }
            }
        }

        // 步骤3：如果 原始字符串中 存在有 出现奇数次的字符，可以 放一个 在中心
        if (oddFrequencyExist) {
            longestPalindromeLength += 1;
        }

        return longestPalindromeLength;
    }
}
