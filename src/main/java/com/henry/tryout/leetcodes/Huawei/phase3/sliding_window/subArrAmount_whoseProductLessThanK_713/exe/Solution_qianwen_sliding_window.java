package com.henry.tryout.leetcodes.Huawei.phase3.sliding_window.subArrAmount_whoseProductLessThanK_713.exe;

public class Solution_qianwen_sliding_window {
    public int numSubarrayProductLessThanK(int[] numArr, int maxLimit) {
        // 🚫 特判：k <= 1 时无解（因为 nums[i] >= 1，乘积 >=1）
        if (maxLimit <= 1) return 0;

        int currWindowLeftCursor = 0;
        int currWindowRightCursor = 0;

        long currentSubArrProduct = 1; // ⚠ 用 long 防止溢出
        int allValidSubArrAmount = 0;

        // 🔁 遍历右边界
        for (int currentNumSpot = 0; currentNumSpot < numArr.length; currentNumSpot++) {
            /* 扩展当前窗口的右边界 */
            currWindowRightCursor = currentNumSpot;

            // （扩展后）即刻维护 当前窗口的乘积
            currentSubArrProduct *= numArr[currWindowRightCursor];

            /* （扩展后）按需维护 ‘当前窗口得合法性’（乘积 严格小于k） */
            // 手段：当 当前窗口的乘积 大于等于k 时，收缩 当前窗口的左边界，直到 子数组的乘积 < k
            while (currWindowLeftCursor <= currWindowRightCursor &&
                    currentSubArrProduct >= maxLimit) {
                // 从乘积中 移除左边界的数字（向0取整）
                currentSubArrProduct /= numArr[currWindowLeftCursor];
                // 把 左边界指针 向后移动一个位置
                currWindowLeftCursor++;
            }

            /* （得到合法的窗口后）计算并累计 当前窗口中 所有 ‘满足条件的连续子数组’ 的数量 */
            // 当前的合法窗口为：[currWindowLeftCursor, currWindowRightCursor]
            // 窗口中 满足条件的连续子数组的数量：(currWindowRightCursor + currWindowLeftCursor - 1);
            // 原理：‘以 currWindowRightCursor 结尾的’合法子数组数量 = 窗口长度
            int validSubArrEndWithRightBarAmount = currWindowRightCursor - currWindowLeftCursor + 1;
            allValidSubArrAmount += validSubArrEndWithRightBarAmount;
        }

        return allValidSubArrAmount;
    }
}
