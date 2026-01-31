package com.henry.tryout.leetcodes.Huawei.phase3.sliding_window.subArrAmount_whoseProductLessThanK_713.exe;

public class Solution_qianwen_sliding_window {
    public int numSubarrayProductLessThanK(int[] numArr, int maxLimit) {
        // 🚫 特判：k <= 1 时无解（因为 nums[i] >= 1，乘积 >=1）
        if (maxLimit <= 1) return 0;

        int leftBarCursor = 0;
        int rightBarCursor = 0;
        long currentSubArrProduct = 1; // ⚠ 用 long 防止溢出
        int allValidSubArrAmount = 0;

        // 🔁 遍历右边界
        for (int currentNumSpot = 0; currentNumSpot < numArr.length; currentNumSpot++) {
            /* （扩展当前窗口）把 当前位置 作为窗口的右边界 */
            rightBarCursor = currentNumSpot;
            // （扩展后）计算当前窗口的乘积
            currentSubArrProduct *= numArr[rightBarCursor];

            /* （扩展后）判断 当前窗口 是否合法（乘积 严格小于k） */
            // 手段：当 当前窗口的乘积 大于等于 k时，收缩 当前窗口的左边界，直到 子数组的乘积 < k
            while (currentSubArrProduct >= maxLimit) {
                // 从乘积中 移除左边界的数字（向0取整）
                currentSubArrProduct /= numArr[leftBarCursor];
                // 把 左边界指针 向后移动一个位置
                leftBarCursor++;
            }

            /* （得到合法的窗口后）计算 窗口中 所有满足条件的 连续子数组的数量 */
            // 当前的合法窗口为：[leftBarCursor, rightBarCursor]
            // 原理：以 rightBarCursor 结尾的合法子数组个数 = 窗口长度
            int validSubArrEndWithRightBarAmount = rightBarCursor - leftBarCursor + 1;
            allValidSubArrAmount += validSubArrEndWithRightBarAmount;
        }

        return allValidSubArrAmount;
    }
}
