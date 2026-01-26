package com.henry.tryout.leetcodes.Huawei.phase1.array.maximum_average_subarray_643.exe;

public class Solution_lingshan_sliding_window {
    public double findMaxAverage(int[] numArr, int fixLength) {
        int maxSubArrSum = Integer.MIN_VALUE; // 窗口元素和的最大值
        int sumOfNumsInCurrentWindow = 0; // 维护窗口元素和

        for (int currentNumSpot = 0; currentNumSpot < numArr.length; currentNumSpot++) {
            // 1. 进入窗口
            int incomingNum = numArr[currentNumSpot];
            sumOfNumsInCurrentWindow += incomingNum;

            // 如果 窗口大小不足 k，说明 还缺少元素，则：
            if (currentNumSpot < fixLength - 1) {
                // 跳过剩余代码，继续 向窗口中添加元素
                continue;
            }

            // 2. 尝试更新 最大和（使用当前窗口的sum）
            maxSubArrSum = Math.max(maxSubArrSum, sumOfNumsInCurrentWindow);

            // 3. 从窗口中 移除 左指针所指向的元素（以便在下一轮循环中，追加新的元素）
            int currentLeftBar = currentNumSpot - fixLength + 1;
            sumOfNumsInCurrentWindow -= numArr[currentLeftBar];
        }
        
        return (double) maxSubArrSum / fixLength;
    }
}
