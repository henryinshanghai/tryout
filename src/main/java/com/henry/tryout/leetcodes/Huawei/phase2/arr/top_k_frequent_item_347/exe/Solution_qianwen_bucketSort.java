package com.henry.tryout.leetcodes.Huawei.phase2.arr.top_k_frequent_item_347.exe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_qianwen_bucketSort {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 统计各个数字的出现频次
        Map<Integer, Integer> numToItsFrequency = new HashMap<>();
        for (int currentNum : nums) {
            numToItsFrequency.put(currentNum,
                    numToItsFrequency.getOrDefault(currentNum, 0) + 1);
        }

        // 2. 创建桶：frequencyToItsNumsList[i] = 出现频次为 i 的所有（不同）数字 所组成的列表
        List<Integer>[] frequencyToItsNumsList = new List[nums.length + 1];
        for (int currentNum : numToItsFrequency.keySet()) { // 🐖 keySet()会对重复数字去重
            int itsFrequency = numToItsFrequency.get(currentNum);

            // 如果 当前位置的元素 还没有初始化，则：
            if (frequencyToItsNumsList[itsFrequency] == null) {
                // 把 元素 初始化为 一个空列表
                frequencyToItsNumsList[itsFrequency] = new ArrayList<>();
            }
            // 把 当前元素 添加到 它所属于的桶(list)中
            frequencyToItsNumsList[itsFrequency].add(currentNum);
        }

        // 3. 遍历桶数组，从 高频率 向 低频率 收集 k个元素
        int[] wantedNumArr = new int[k];
        int ordinalNo = 0;
        for (int currentFreq = frequencyToItsNumsList.length - 1;
             currentFreq >= 0 && ordinalNo < k; // ordinalNo < k 是为了 防止 外层循环空转
             currentFreq--) {
            // 如果 当前频率 的桶 不为null，
            if (frequencyToItsNumsList[currentFreq] != null) {
                // 遍历 桶中的所有元素
                for (int currentNum : frequencyToItsNumsList[currentFreq]) {
                    // 把 桶中的元素 按序添加到 结果序列中
                    wantedNumArr[ordinalNo++] = currentNum;
                    // 添加到 预期数量后，则 跳出循环
                    if (ordinalNo == k) break;
                }
            }
        }

        // 返回 添加了 频率最高的k个元素的数组
        return wantedNumArr;
    }
}
