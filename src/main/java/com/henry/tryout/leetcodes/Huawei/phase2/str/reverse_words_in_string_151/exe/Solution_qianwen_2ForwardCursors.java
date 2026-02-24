package com.henry.tryout.leetcodes.Huawei.phase2.str.reverse_words_in_string_151.exe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 正向移动 句子字符指针、单词字符指针；
// 🐖 收集单词后，还需要进行逆序处理
public class Solution_qianwen_2ForwardCursors {
    public String reverseWords(String sentence) {
        List<String> resultWordList = new ArrayList<>();

        int currentSentenceCharCursor = 0;
        int charAmount = sentence.length();

        while (currentSentenceCharCursor < charAmount) {
            /* 找到 当前单词的开头位置 */
            // 如果 指针指向的当前字符 是 空格，
            while (currentSentenceCharCursor < charAmount &&
                    sentence.charAt(currentSentenceCharCursor) == ' ') {
                // 则：跳过空格
                currentSentenceCharCursor++;
            } /* 循环结束后，句子字符指针 会指在 当前单词的开头 */

            // 如果 句子字符指针 已经超过 句子的字符长度，
            // 说明 句子已经处理完成，
            if (currentSentenceCharCursor >= charAmount) {
                // 则：终止循环
                break;
            }

            /* 找到 当前单词的末尾位置 */
            int currentWordCharCursor = currentSentenceCharCursor;
            // 当前单词 结束于 下一个空格
            while (currentWordCharCursor < charAmount &&
                    sentence.charAt(currentWordCharCursor) != ' ') {
                currentWordCharCursor++;
            } /* 循环结束后，单词字符指针 会停在 该空格字符上 */

            /* 截取当前单词，并 把它追加到结果单词列表中 */
            // 截取当前单词: sentenceStr.substring(<start_spot>, <end_spot>)
            // 🐖 end_spot不包含
            String currentWord =
                    sentence.substring(currentSentenceCharCursor,
                            currentWordCharCursor);
            resultWordList.add(currentWord);

            // 把 句子字符指针 快进到 当前单词的单词字符指针 处
            // 以便 继续处理 下一个单词
            currentSentenceCharCursor = currentWordCharCursor;
        }

        // 对 所有收集到的单词 进行 逆序处理
        Collections.reverse(resultWordList);
        // （逆序后）把 逆序排列的单词 使用 空格 连接起来，得到 单词反转的句子
        return String.join(" ", resultWordList);
    }
}
