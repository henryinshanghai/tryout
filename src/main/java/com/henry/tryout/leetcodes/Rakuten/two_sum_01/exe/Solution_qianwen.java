package com.henry.tryout.leetcodes.Rakuten.two_sum_01.exe;

import java.util.HashMap;
import java.util.Map;

// 🐖 不能预构建map，而是边遍历边构建
public class Solution_qianwen {
    public int[] twoSum(int[] nums, int target) {
        // 记录 元素 -> 其位置 的映射
        Map<Integer, Integer> numToItsSpotMap = new HashMap();

        for (int currentSpot = 0; currentSpot < nums.length; currentSpot++) {
            int currentNum = nums[currentSpot];
            int expectedNum = target - currentNum;

            // ① 先检查 补数元素的存在性
            if (numToItsSpotMap.containsKey(expectedNum)) {
                int partnerNumSpot = numToItsSpotMap.get(expectedNum);
                return new int[]{currentSpot, partnerNumSpot};
            }

            // ② 再添加 当前元素的条目
            numToItsSpotMap.put(currentNum, currentSpot);
        }

        return new int[]{-1, -1};
    }
}
