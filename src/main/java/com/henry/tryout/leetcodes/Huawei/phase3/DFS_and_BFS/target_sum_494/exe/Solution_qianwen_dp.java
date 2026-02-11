package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.target_sum_494.exe;

public class Solution_qianwen_dp {
    public int findTargetSumWays(int[] numArr, int targetSum) {
        int totalSum = 0;
        for (int currentNum : numArr) {
            totalSum += currentNum;
        }

        // 边界检查
        if (Math.abs(targetSum) > totalSum) return 0; // target 太大
        if ((targetSum + totalSum) % 2 != 0) return 0; // 无法整除

        // 问题的等价转化
        /*
            已知：
                abs(addPlus_sum) + abs(addMinus_sum) = sum;
                abs(addPlus_sum) - abs(addMinus_sum) = target;
            => abs(addPlus_sum) = (target + sum) / 2;
         */
        // 原问题 ⇨ 0-1背包问题：使用 特定的物品选项(只能选择1次) 来 装满 容量为bagCapacity的背包，最多有 多少种装法？
        int bagCapacity = (targetSum + totalSum) / 2;
        if (bagCapacity < 0) return 0; // bagCapacity 不能为负

        // currentSumToItsSchemaAmount[j]：得到加和结果j 有dp[j]种选择方案；
        int[] currentSumToItsSchemaAmount = new int[bagCapacity + 1];
        currentSumToItsSchemaAmount[0] = 1; // 空集

        // 0-1背包的写法：① 先物品，② 后背包，③ 内层循环倒序
        for (int currentNum : numArr) {
            for (int currentSum = bagCapacity; currentSum >= currentNum; currentSum--) { // 背包容量递减
                /* “组合类0-1背包问题”的一般递推公式：dp[j] += dp[j-nums[i]] */
                // dp[j-nums[i]] 表示 刨掉nums[i]的空间 时，会有 多少种方案 填满 剩余空间。
                // 填满 空间j的方案数量 = 所有 刨掉当前元素后的 填满方案数量 相累加。
                currentSumToItsSchemaAmount[currentSum] +=
                        currentSumToItsSchemaAmount[currentSum - currentNum];
            }
        }

        // 从dp[]数组中 返回满足题意的元素
        return currentSumToItsSchemaAmount[bagCapacity];
    }
}
