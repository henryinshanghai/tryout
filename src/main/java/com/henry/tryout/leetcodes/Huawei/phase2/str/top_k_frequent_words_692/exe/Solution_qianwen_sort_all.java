package com.henry.tryout.leetcodes.Huawei.phase2.str.top_k_frequent_words_692.exe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_qianwen_sort_all {
    public List<String> topKFrequent(String[] words, int k) {
        // 1. 统计词频
        Map<String, Integer> wordToItsFrequency = new HashMap<>();
        for (String currentWord : words) {
            wordToItsFrequency.put(currentWord,
                    wordToItsFrequency.getOrDefault(currentWord, 0) + 1);
        }

        // 2. 把 单词的去重集合 转为 列表
        List<String> wordList = new ArrayList<>(wordToItsFrequency.keySet());

        // 3. 自定义 排序规则
        Collections.sort(wordList, (a, b) -> {
            int freqA = wordToItsFrequency.get(a);
            int freqB = wordToItsFrequency.get(b);
            // ① 频率优先 - 高频的单词在前面（频率降序）
            if (freqA != freqB) {
                return freqB - freqA;
            }
            // ② 字典序规则 次之 - 字母表 靠后的单词 排在后面（字典序升序）
            // 手段：直接使用 字符串对象的compareTo() 就能得到 字典序升序的 比较结果
            return a.compareTo(b);
        });

        // 4. 返回 按指定规则排序后的 单词列表中的 前k个单词
        return wordList.subList(0, k);
    }
}
