package com.henry.tryout.leetcodes.Huawei.phase2.arr.top_k_frequent_item_347.exe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_qianwen_sortAll {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 统计各个数字的频次
        Map<Integer, Integer> numToItsFrequency = new HashMap<>();
        for (int currentNum : nums) {
            numToItsFrequency.put(currentNum,
                    numToItsFrequency.getOrDefault(currentNum, 0) + 1);
        }

        // 2. 得到（去重后）数字的列表 并 按频率 降序排序
        // 手段：① new ArrayList<>()；② list.sort(<lambda_expression>)
        List<Integer> numSeqSortByFreqInDesc = new ArrayList<>(numToItsFrequency.keySet());
        numSeqSortByFreqInDesc.sort(
                (a, b) ->
                        numToItsFrequency.get(b) - numToItsFrequency.get(a)); // 降序排列（高频在前）

        // 3. 从 降序排列的列表 中 取 前k个
        return numSeqSortByFreqInDesc
                .stream() // 转化为流（以使用流式操作 处理列表中的元素）
                .mapToInt(i -> i)// 把Integer 转化为 int??
                .limit(k) // 截断 流
                .toArray(); // 把 流 收集为 int[]数组
    }
}
