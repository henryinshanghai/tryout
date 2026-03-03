package com.henry.tryout.leetcodes.Rakuten.max_product_subArr_152.exe;

public class Solution_2_dp_arr {
    public int maxProduct(int[] nums) {
        int numAmount = nums.length;

        /* 准备dp[]数组 */
        // i：元素位置；dp1[i]:以i作为结束位置的子数组的最大乘积
        int[] currSpotToMaxProductOfSubArrEndWithIt = new int[numAmount];
        // i：元素位置；dp1[i]:以i作为结束位置的子数组的最小乘积
        // 原因：因为 负数元素 乘入当前乘积后，会反转 当前的最大乘积 与 最小乘积
        int[] currSpotToMinProductOfSubArrEndWithIt = new int[numAmount];

        /* dp[]元素的初始化 */
        currSpotToMaxProductOfSubArrEndWithIt[0] = nums[0];
        currSpotToMinProductOfSubArrEndWithIt[0] = nums[0];

        // 准备一个变量   用于维护‘乘积最大的子数组’的最大乘积
        // 🐖 初始值 需要设置为 当前子数组的结果（因为 如果没能找到 更大的乘积，它会 被直接返回）
        int maxProductOfAllSubArr = nums[0];

        /* 遍历顺序 */
        for (int currentSpot = 1; currentSpot < nums.length; currentSpot++) {
            int currentNum = nums[currentSpot];
            /* 在当前元素为负数时，交换 maxProduct 与 minProduct */
            if (currentNum < 0) {
                int temp = currSpotToMaxProductOfSubArrEndWithIt[currentSpot - 1];
                currSpotToMaxProductOfSubArrEndWithIt[currentSpot - 1] = currSpotToMinProductOfSubArrEndWithIt[currentSpot - 1];
                currSpotToMinProductOfSubArrEndWithIt[currentSpot - 1] = temp;
            }

            // 维护 ‘以i作为结束位置的子数组’的最大乘积
            currSpotToMaxProductOfSubArrEndWithIt[currentSpot] = Math.max(
                    currSpotToMaxProductOfSubArrEndWithIt[currentSpot - 1]
                            * currentNum, // option1 使用 当前元素 来 扩展当前子数组
                    currentNum // option2 使用 当前元素 来 重开当前子数组
            );

            // 维护 ‘以i作为结束位置的子数组’的最小乘积
            currSpotToMinProductOfSubArrEndWithIt[currentSpot] = Math.min(
                    currSpotToMinProductOfSubArrEndWithIt[currentSpot - 1]
                            * currentNum, // option1 使用 当前元素 来 扩展当前子数组
                    currentNum // option2 使用 当前元素 来 重开当前子数组
            );

            // 维护 ‘最大乘积’
            maxProductOfAllSubArr =
                    Math.max(maxProductOfAllSubArr, currSpotToMaxProductOfSubArrEndWithIt[currentSpot]);
        }

        // 返回‘最大乘积’
        return maxProductOfAllSubArr;
    }
}
