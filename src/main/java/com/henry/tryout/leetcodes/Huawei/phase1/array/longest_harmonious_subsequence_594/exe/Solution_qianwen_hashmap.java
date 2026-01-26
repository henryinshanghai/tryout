package com.henry.tryout.leetcodes.Huawei.phase1.array.longest_harmonious_subsequence_594.exe;

import java.util.HashMap;
import java.util.Map;

public class Solution_qianwen_hashmap {
    public int findLHS(int[] nums) {
        // 步骤1: 统计每个数字的频率
        Map<Integer, Integer> numToItsFrequency = new HashMap<>();

        for (int currentNum : nums) {
            // 对于 当前数字，添加/更新 其所对应的map条目
            numToItsFrequency.put(currentNum,
                    numToItsFrequency.getOrDefault(currentNum, 0) + 1); // getOrDefault():如果key存在，则 返回结果；不存在，则返回指定的默认值
        }

        int maxHarmoniousSeqLength = 0;

        // 步骤2: 遍历 每个唯一数字 x
        for (int currentNum : numToItsFrequency.keySet()) {
            // 检查 currentNum+1 是否存在
            if (numToItsFrequency.containsKey(currentNum + 1)) {
                // 计算 当前和谐子序列的长度 = count(currentNum) + count(currentNum+1)
                int currentLength = numToItsFrequency.get(currentNum) + numToItsFrequency.get(currentNum + 1);
                // 尝试 使用当前和谐子序列的长度 来 更新 最长子序列的长度
                maxHarmoniousSeqLength = Math.max(maxHarmoniousSeqLength, currentLength);
            }
        }

        return maxHarmoniousSeqLength;
    }
}
