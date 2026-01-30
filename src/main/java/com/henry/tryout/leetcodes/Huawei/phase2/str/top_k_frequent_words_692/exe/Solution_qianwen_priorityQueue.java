package com.henry.tryout.leetcodes.Huawei.phase2.str.top_k_frequent_words_692.exe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_qianwen_priorityQueue {
    public List<String> topKFrequent(String[] words, int k) {
        // 1. 统计词频
        Map<String, Integer> wordToItsFrequency = new HashMap<>();
        for (String currentWord : words) {
            wordToItsFrequency.put(currentWord,
                    wordToItsFrequency.getOrDefault(currentWord, 0) + 1);
        }

        // 2. 创建一个大小为k的优先队列
        // 🐖 根据题目要求 按需指定 队列元素的比较规则（刚好相反）
        PriorityQueue<String> wordMinQueue =
                new PriorityQueue<>((a, b) -> { // 🐖 不要遗漏<>
                    int freqA = wordToItsFrequency.get(a);
                    int freqB = wordToItsFrequency.get(b);

                    if (freqA != freqB) {
                        return freqA - freqB; // 按 频率 升序（小的在顶，会先被淘汰）
                    }
                    return b.compareTo(a);    // 按 字典序 降序（大的在顶，会先被淘汰）→ 保证 字典序小的 留下
                });

        // 3. 维护 队列元素
        // 手段：遍历单词集合，不断入队 当前单词，按需出队
        for (String currentWord : wordToItsFrequency.keySet()) {
            // 把 当前单词 添加到 堆中
            wordMinQueue.offer(currentWord);

            // 马上 检查 堆中元素的数量 是不是 已经大于k
            // 如果 是，说明 需要把 不符合需要的元素（当前频率最小的单词 or 当前字典序最大的单词） 弹出，
            if (wordMinQueue.size() > k) {
                // 则：弹出 不需要的元素
                wordMinQueue.poll();
            }
        }

        // 4. 倒序输出（堆顶是最小，我们要最大在前）
        // ① 把 最小堆中的元素 从小到大地 添加到列表中
        List<String> wordsResultList = new ArrayList<>();
        while (!wordMinQueue.isEmpty()) {
            // 删除并获取 堆中地最小元素
            wordsResultList.add(wordMinQueue.poll());
        }

        // ② 然后 把从小到大排列的元素 反转，得到 从大到小排列的元素（频率降序、字典序升序）
        Collections.reverse(wordsResultList);
        return wordsResultList;
    }
}
