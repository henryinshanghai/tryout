package com.henry.tryout.leetcodes.Rakuten.max_product_subArr_152.exe;

public class Solution_huashou_2Vars {
    public int maxProduct(int[] nums) {
        // 准备一个变量   用于维护‘乘积最大的子数组’的乘积
        int maxProduct = Integer.MIN_VALUE,
                // 准备一个变量   用于维护‘以当前元素作为末尾元素的子数组’的最大乘积
                maxProductEndWithCurrSpot = 1,
                // 准备一个变量   用于维护‘以当前元素作为末尾元素的子数组’的最小乘积
                minProductEndWithCurrSpot = 1;

        for (int currentSpot = 0; currentSpot < nums.length; currentSpot++) {
            // 如果 当前的数组元素 是一个负数，
            // 说明 在把它乘进去 maxProduct之前 需要先交换 maxProduct与minProduct 以得到最大乘积，
            if (nums[currentSpot] < 0) {
                // 则：交换 maxProductEndWithCurrSpot 与 minProductEndWithCurrSpot
                int tmp = maxProductEndWithCurrSpot;
                maxProductEndWithCurrSpot = minProductEndWithCurrSpot;
                minProductEndWithCurrSpot = tmp;
            }

            // 维护 ‘以当前元素作为末尾元素的子数组’的 最大乘积
            maxProductEndWithCurrSpot =
                    Math.max(maxProductEndWithCurrSpot * nums[currentSpot],
                            nums[currentSpot]);

            // 维护 ‘以当前元素作为末尾元素的子数组’的 最小乘积
            minProductEndWithCurrSpot =
                    Math.min(minProductEndWithCurrSpot * nums[currentSpot],
                            nums[currentSpot]);

            // 使用 ‘以当前元素作为末尾元素的子数组’的 最大乘积 来 尝试更新 ‘乘积最大的子数组’的乘积
            maxProduct = Math.max(maxProduct, maxProductEndWithCurrSpot);
        }

        return maxProduct;
    }
}
