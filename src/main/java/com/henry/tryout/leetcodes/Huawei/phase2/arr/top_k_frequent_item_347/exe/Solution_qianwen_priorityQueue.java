package com.henry.tryout.leetcodes.Huawei.phase2.arr.top_k_frequent_item_347.exe;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_qianwen_priorityQueue {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 统计各个数字的出现频次
        Map<Integer, Integer> numToItsFrequency = new HashMap<>();
        for (int currentNum : nums) {
            numToItsFrequency.put(currentNum,
                    numToItsFrequency.getOrDefault(currentNum, 0) + 1);
        }

        // 2. 创建 最小优先队列（Java默认就是 小顶堆）   作用：能够方便地剔除 当前最小的元素
        PriorityQueue<Integer> numMinQueuePrioritizeByFreq =
                new PriorityQueue<>(
                        // 指定比较器，用于定义 队列中元素的优先级依据(也就是 堆中节点大小的 比较依据)
                        Comparator.comparingInt(numToItsFrequency::get)
                );

        // 3. 遍历所有唯一元素
        for (int currentNum : numToItsFrequency.keySet()) { // 🐖 遍历数字集合（避免重复数字）
            /* 标准写法，无需显示比较（显示比较的写法 易出错） */
            numMinQueuePrioritizeByFreq.offer(currentNum);

            if(numMinQueuePrioritizeByFreq.size() > k) {
                numMinQueuePrioritizeByFreq.poll();
            }
        }

        // 4. 把 优先队列中的元素 转成 数组   手段：流式处理
        return numMinQueuePrioritizeByFreq
                .stream()   // 转化为Stream对象
                .mapToInt(i -> i)// 把 包装类型 转化为 基本类型
                .toArray(); // 把结果写到数组中
    }
}
