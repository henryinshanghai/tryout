package com.henry.tryout.leetcodes.Huawei.phase2.str.reverse_words_in_string_151.exe;

// 反向移动字符指针
public class Solution_Krahets_2BackCursors {
    public String reverseWords(String originalSentence) {
        // 删除首尾空格
        originalSentence = originalSentence.trim();

        int backwardsWordEndCursor = originalSentence.length() - 1,
                backwardsSentenceCharCursor = backwardsWordEndCursor;

        StringBuilder resultStr = new StringBuilder();

        while (backwardsSentenceCharCursor >= 0) {
            /* 找到/识别 当前字符串的开始位置 */
            // 把 字符指针 停留在 所遇到的首个空格字符（当前单词的开始字符的前一个位置）位置上
            while (backwardsSentenceCharCursor >= 0 &&
                    originalSentence.charAt(backwardsSentenceCharCursor) != ' ') {
                backwardsSentenceCharCursor--;
            }

            /* 追加 识别出的字符串 到结果字符串中 */
            // 从原始字符串中，拷贝指定范围内（左闭右开）的子串（单词），追加到目标字符串中
            resultStr.append(originalSentence, backwardsSentenceCharCursor + 1, backwardsWordEndCursor + 1)
                    .append(" "); // 添加单词

            /* 找到/识别 字符串的末尾位置 */
            // 把 字符指针 停留在 所遇到的首个非空格字符位置（下一个单词的末尾位置）上
            while (backwardsSentenceCharCursor >= 0 &&
                    originalSentence.charAt(backwardsSentenceCharCursor) == ' ') {
                backwardsSentenceCharCursor--;
            }

            // 移动 ‘当前单词的词尾字符指针’ 到 正确的位置上
            backwardsWordEndCursor = backwardsSentenceCharCursor;
        }

        // 转化为字符串并返回
        return resultStr.toString().trim();
    }
}
