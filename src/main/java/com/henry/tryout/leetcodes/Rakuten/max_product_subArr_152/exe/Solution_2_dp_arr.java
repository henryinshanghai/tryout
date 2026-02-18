package com.henry.tryout.leetcodes.Rakuten.max_product_subArr_152.exe;

public class Solution_2_dp_arr {
    public int maxProduct(int[] nums) {
        int numAmount = nums.length;

        /* 准备dp[]数组 */
        // i：元素位置；dp1[i]:以i作为结束位置的子数组的最大乘积
        int[] currentSpotToMaxProductEndWithIt = new int[numAmount];
        // i：元素位置；dp1[i]:以i作为结束位置的子数组的最小乘积
        int[] currentSpotToMinProductEndWithIt = new int[numAmount];

        /* dp[]元素的初始化 */
        currentSpotToMaxProductEndWithIt[0] = nums[0];
        currentSpotToMinProductEndWithIt[0] = nums[0];

        // 准备一个变量   用于维护‘乘积最大的子数组’的最大乘积
        int maxProduct = nums[0];

        /* 遍历顺序 */
        for (int currentSpot = 1; currentSpot < nums.length; currentSpot++) {
            int currentNum = nums[currentSpot];
            /* 在当前元素为负数时，交换 maxProduct 与 minProduct */
            if (currentNum < 0) {
                int temp = currentSpotToMaxProductEndWithIt[currentSpot - 1];
                currentSpotToMaxProductEndWithIt[currentSpot - 1] = currentSpotToMinProductEndWithIt[currentSpot - 1];
                currentSpotToMinProductEndWithIt[currentSpot - 1] = temp;
            }

            // 维护 ‘以i作为结束位置的子数组’的最大乘积
            currentSpotToMaxProductEndWithIt[currentSpot] = Math.max(
                    currentSpotToMaxProductEndWithIt[currentSpot - 1] * currentNum, // option1 乘进来 当前元素
                    currentNum // option2 从当前元素 重开子数组
            );

            // 维护 ‘以i作为结束位置的子数组’的最小乘积
            currentSpotToMinProductEndWithIt[currentSpot] = Math.min(
                    currentSpotToMinProductEndWithIt[currentSpot - 1] * currentNum, // option1 乘进来 当前元素
                    currentNum // option2 从当前元素 重开子数组
            );

            // 维护 ‘最大乘积’
            maxProduct = Math.max(maxProduct, currentSpotToMaxProductEndWithIt[currentSpot]);
        }

        // 返回‘最大乘积’
        return maxProduct;
    }
}
