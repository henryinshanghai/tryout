package com.henry.tryout.leetcodes.Huawei.phase1.array.maximum_average_subarray_643.exe;

public class Solution_qianwen_sliding_window {
    public double findMaxAverage(int[] numArr, int subArrLength) {
        // 步骤1: 计算 第一个窗口的和
        long sumOfNumsInWindow = 0; // 用 long 防止整数溢出
        for (int currentSpot = 0; currentSpot < subArrLength; currentSpot++) {
            sumOfNumsInWindow += numArr[currentSpot];
        }

        long maxSubArrSum = sumOfNumsInWindow;

        // 步骤2: 把滑动窗口向右滑动（从第k个元素 开始）
        for (int currentSpot = subArrLength; currentSpot < numArr.length; currentSpot++) {
            // 移除 左边元素，添加 右边元素
            // 🐖 由于 currentSpot已经是 窗口右边界的下一个位置了，因此 这里求窗口左边界时的公式为：currentSpot - fixLength，而不需要再+1了
            int numOnLeftBar = numArr[currentSpot - subArrLength];
            int incomingNum = numArr[currentSpot];

            // 计算 当前滑动窗口的sum
            sumOfNumsInWindow = sumOfNumsInWindow - numOnLeftBar + incomingNum;

            // 尝试更新 最大子数组的sum
            maxSubArrSum = Math.max(maxSubArrSum, sumOfNumsInWindow);
        }

        // 步骤3: 计算 sum最大的子数组的平均数（aka 最大平均数），并返回
        return (double) maxSubArrSum / subArrLength;
    }
}
