package com.henry.tryout.leetcodes.Huawei.phase1.array.two_sum_1.exe;

import java.util.HashMap;
import java.util.Map;

public class Solution_qianwen_map {
    public int[] twoSum(int[] nums, int target) {
        // 创建哈希表：key = 数值，value = 该数值的下标
        Map<Integer, Integer> numToItsSpot = new HashMap<>();

        // 一次遍历数组
        for (int currentSpot = 0; currentSpot < nums.length; currentSpot++) {
            int currentNum = nums[currentSpot];
            int itsComplementNum = target - currentNum; // 需要找的另一个数

            // 如果 itsComplementNum 已经在哈希表中，说明 找到了答案
            if (numToItsSpot.containsKey(itsComplementNum)) {
                return new int[]{numToItsSpot.get(itsComplementNum), currentSpot};
            }

            // 把 当前数字 和 它的下标 存入哈希表（供后续查找）
            numToItsSpot.put(currentNum, currentSpot);
        }

        // 题目保证有 唯一解，所以这里 不会执行
        throw new IllegalArgumentException("No solution");
    }
}
