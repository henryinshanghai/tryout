package com.henry.tryout.leetcodes.Huawei.phase1.str.longest_palindrome_409.exe;

// 核心表达式：i - (i & 1) <=>  这等价于 i / 2 * 2，但用 位运算 更高效
public class Solution_Sweetiee_frequency {
    public int longestPalindrome(String s) {
        // 从A-z之间，一共是有58个字符。使用数组中的一个位置 来 表示一个字符
        int[] charToItsFrequency = new int[58];

        // 统计 字符串中 每个字符出现的频率
        for (char currentChar : s.toCharArray()) {
            // ① 计算 当前字符 在数组中所对应的下标；
            // 手段：(currentChar-'A') 表示的是 当前字符 相对于 大写字母'A'的偏移量。我们把它用作 字符在数组中对应的下标
            // ② 对 该字符 进行计数；
            // 手段：每次字符出现时，就 把 该字符所对应的元素值(频率) + 1
            charToItsFrequency[currentChar - 'A'] += 1;
        }

        int longestPalinLength = 0;
        for (int currentFrequency : charToItsFrequency) {
            /* 构造 回文字符串 时，字符 最多 用偶数次（<= 其在原始字符串中出现的次数） */
            // 判断 当前频率的奇偶性；
            // 手段（位运算写法）：<int> & 1;
            // 用法：结果为0，说明<int>为偶数；结果为1，说明<int>为奇数。
            int zeroOrOne = currentFrequency & 1;
            int maxAvailableTimes = currentFrequency - zeroOrOne;

            // 把 字符被用到的次数 作为 回文字符串中的字符数量 累计进 回文字符串的长度
            longestPalinLength += maxAvailableTimes;
        }

        // 如果 最终的长度 小于 原字符串的长度，说明 里面某个字符 出现了 奇数次，
        // 那么 那个字符 可以放在 回文串的中间，所以 额外再加一。
        return longestPalinLength < s.length() ? longestPalinLength + 1 : longestPalinLength;
    }
}
