package com.henry.tryout.leetcodes.Huawei.phase2.str.reverse_words_in_string_151.exe;

public class Solution_qianwen_reverseTwice {
    public String reverseWords(String sentence) {
        // 1. 去除 多余空格（手写）
        StringBuilder trimmedSentence = removeExtraSpaces(sentence);

        // 2. 反转 整个字符串
        reverse(trimmedSentence, 0, trimmedSentence.length() - 1);

        // 3. 反转 每个单词
        reverseEachWord(trimmedSentence);

        return trimmedSentence.toString();
    }

    private StringBuilder removeExtraSpaces(String originalSentence) {
        StringBuilder trimmedSentence = new StringBuilder();

        int currentSentenceCharCursor = 0,
                charAmount = originalSentence.length();

        while (currentSentenceCharCursor < charAmount) {
            // 如果 当前字符 不是 空格字符，说明 需要把该字符 追加到结果字符串中，则：
            if (originalSentence.charAt(currentSentenceCharCursor) != ' ') {
                // ① 如果 结果字符串 不是刚开始构建，说明 需要在单词后 添加一个空格，则：
                if (trimmedSentence.length() > 0) {
                    // 向结果字符串中 追加一个空格
                    trimmedSentence.append(' ');
                }

                /* ② 把 当前单词中的每个字符 都添加到 结果字符串中 */
                int currentWordCharCursor = currentSentenceCharCursor;
                // 当前单词的字符 结束于 下一个空格
                while (currentWordCharCursor < charAmount && originalSentence.charAt(currentWordCharCursor) != ' ') {
                    // 追加后，把 单词字符指针 向后移动一个位置
                    trimmedSentence.append(originalSentence.charAt(currentWordCharCursor++));
                }

                // ③（当前单词追加完成后）把 句子字符指针 快进到 单词字符指针位置
                currentSentenceCharCursor = currentWordCharCursor;
            } else { // 否则，当前字符 是 空格字符，则：
                // 把 字符指针 向后移动一个位置
                currentSentenceCharCursor++;
            }
        }

        // 返回 修剪好的字符串
        return trimmedSentence;
    }

    // 反转 指定字符串 在指定区间中的 所有字符
    private void reverse(StringBuilder trimmedSentence, int leftBarCursor, int rightBarCursor) {
        while (leftBarCursor < rightBarCursor) {
            // 记录下 当前左指针 所指向的字符
            char tmp = trimmedSentence.charAt(leftBarCursor);
            // 使用 右指针所指向的字符 来 设置左指针指向的位置；然后把 左指针 向后移动一个位置
            trimmedSentence.setCharAt(leftBarCursor++, trimmedSentence.charAt(rightBarCursor));
            // 使用 左指针所指向的字符 来 设置右指针所指向的位置；然后 把右指针 向前移动一个位置
            trimmedSentence.setCharAt(rightBarCursor--, tmp);
        } /* 循环结束后，句子就已经被 按字符反转了 */
    }

    private void reverseEachWord(StringBuilder sentence) {
        int sentenceCharAmount = sentence.length();
        int currentWordStartCursor = 0;

        for (int currentSentenceCharSpot = 0; currentSentenceCharSpot <= sentenceCharAmount; currentSentenceCharSpot++) {
            // 情形① 游标指针到达句子的末尾； 情形② 游标指针当前指向的字符是空格
            if (currentSentenceCharSpot == sentenceCharAmount
                    || sentence.charAt(currentSentenceCharSpot) == ' ') {
                // 反转 当前单词； 单词的所属区间 [currentWordStartCursor, currentSentenceCharSpot - 1]
                reverse(sentence, currentWordStartCursor, currentSentenceCharSpot - 1);

                // 把 单词起始位置指针 移动到 下一个单词的起始位置
                currentWordStartCursor = currentSentenceCharSpot + 1;
            }
        }
    }
}
