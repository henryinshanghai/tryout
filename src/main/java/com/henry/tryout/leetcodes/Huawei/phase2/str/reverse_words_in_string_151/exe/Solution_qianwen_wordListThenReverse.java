package com.henry.tryout.leetcodes.Huawei.phase2.str.reverse_words_in_string_151.exe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 正向移动 字符指针
public class Solution_qianwen_wordListThenReverse {
    public String reverseWords(String sentence) {
        List<String> resultWordList = new ArrayList<>();
        int currentSentenceCharCursor = 0, charAmount = sentence.length();

        while (currentSentenceCharCursor < charAmount) {
            // 如果 指针指向的当前字符 是 空格，则：
            while (currentSentenceCharCursor < charAmount
                    && sentence.charAt(currentSentenceCharCursor) == ' ') {
                // 跳过空格
                currentSentenceCharCursor++;
            } /* 跳过空格后，字符指针 会指在 单词的开头 */

            // 如果 字符指针 已经超过 句子的字符长度，说明 句子已经处理完成，则：
            if (currentSentenceCharCursor >= charAmount) {
                // 终止循环
                break;
            }

            /* 找到 当前单词 的结束位置 */
            int currentWordCharCursor = currentSentenceCharCursor;
            // 当前单词 结束于 下一个空格（单词字符指针 会停在 该空格字符上）
            while (currentWordCharCursor < charAmount &&
                    sentence.charAt(currentWordCharCursor) != ' ') {
                currentWordCharCursor++;
            }
            // 使用两个指针（单词开头，单词末尾） 来 提取当前单词 [currentSentenceCharCursor, currentWordCharCursor)
            String currentWord = sentence.substring(currentSentenceCharCursor, currentWordCharCursor);
            resultWordList.add(currentWord);

            // 把 句子字符指针 快进到 当前单词的单词字符指针 处 - 继续处理下一个单词
            currentSentenceCharCursor = currentWordCharCursor;
        }

        // 对 所有收集到的单词 进行 逆序处理
        Collections.reverse(resultWordList);
        // 然后 把 逆序排列的单词 使用 空格 连接起来，得到 单词反转的句子
        return String.join(" ", resultWordList);
    }
}
