package com.henry.tryout.leetcodes.Rakuten.max_product_subArr_152.exe;

public class Solution_qianwen {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        // #Q1：初始值应该怎么设置?
        int currMaintainedMaxProd = nums[0];
        int currMaintainedMinProd = nums[0];
        int maxProduct = nums[0];

        // 🐖 这里是从spot=1开始计算的
        for (int currentSpot = 1; currentSpot < nums.length; currentSpot++) {
            // #Q2: 为什么这里需要使用 临时变量呢?
            int tempMax = Math.max(nums[currentSpot], // 当前位置的数组元素
                    Math.max(currMaintainedMaxProd * nums[currentSpot], // 当前记录的最大乘积 * 当前位置的数组元素
                            currMaintainedMinProd * nums[currentSpot])); // 当前记录的最小乘积 * 当前位置的数组元素（负数 * 负数）

            int tempMin = Math.min(nums[currentSpot], // 当前位置的数组元素
                    Math.min(currMaintainedMaxProd * nums[currentSpot], // 当前记录的最大乘积 * 当前位置的数组元素（正数 * 负数）
                            currMaintainedMinProd * nums[currentSpot])); // 当前记录的最小乘积 * 当前位置的数组元素（负数 * 正数）

            currMaintainedMaxProd = tempMax;
            currMaintainedMinProd = tempMin;

            maxProduct = Math.max(maxProduct, currMaintainedMaxProd);
        }

        return maxProduct;
    }
}
