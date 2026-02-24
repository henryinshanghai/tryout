package com.henry.tryout.leetcodes.Huawei.phase2.str.reverse_words_in_string_151.exe;

// 反向移动 句子字符指针、单词结束字符指针
// 🐖 收集完成就得到了结果，不需要逆序处理
public class Solution_Krahets_2BackwardCursors {
    public String reverseWords(String originalSentence) {
        // ① 删除句子的首尾空格
        originalSentence = originalSentence.trim();

        // 准备 句子字符指针
        int backwardsWordEndCursor = originalSentence.length() - 1;
        // 准备 单词末尾字符指针
        int backwardsSentenceCharCursor = backwardsWordEndCursor;

        StringBuilder resultStr = new StringBuilder();

        while (backwardsSentenceCharCursor >= 0) {
            /* ② 找到 当前单词的开始位置 */
            // 把 句子字符指针 停留在 所遇到的首个空格字符（当前单词的开始字符的前一个位置）位置上
            while (backwardsSentenceCharCursor >= 0 &&
                    originalSentence.charAt(backwardsSentenceCharCursor) != ' ') {
                backwardsSentenceCharCursor--;
            } /* 循环结束后，句子字符指针的下一个位置 就是 当前单词的开头 */

            /* ③ 截取出 当前单词，并 把它追加到 结果字符串中 */
            // 手段：target_str.append(<original_str>, start_spot, end_spot)
            // 🐖 end_spot上的字符 不会被包含
            resultStr.append(originalSentence,
                            backwardsSentenceCharCursor + 1,
                            backwardsWordEndCursor + 1) // 添加单词
                    .append(" "); // 追加空格

            /* ④ 找到 下一个单词的末尾位置 */
            // 把 句子字符指针 停留在 所遇到的首个非空格字符位置（下一个单词的末尾位置）上
            while (backwardsSentenceCharCursor >= 0 &&
                    originalSentence.charAt(backwardsSentenceCharCursor) == ' ') {
                backwardsSentenceCharCursor--;
            } /* 循环结束后，句子字符指针 指向 下一个单词的结束位置 */

            // 移动 ‘当前单词的词尾字符指针’ 到 该位置上
            backwardsWordEndCursor = backwardsSentenceCharCursor;
        }

        // 转化为字符串（删除多余空格） 并返回
        return resultStr.toString().trim();
    }
}
