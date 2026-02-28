package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.target_sum_494.exe;

public class Solution_qianwen_dp {
    public int findTargetSumWays(int[] numArr, int targetSum) {
        int totalSum = 0;
        for (int currentNum : numArr) {
            totalSum += currentNum;
        }

        /* 边界检查 */
        // 如果 target 比起 非负整数序列 所有元素都选择+得到的结果 还要打，
        // 说明 不可能存在任何可行方案，
        if (Math.abs(targetSum) > totalSum) {
            // 则：返回0
            return 0;
        }
        // 如果 (target + totalSum) 无法被2整除，
        // 说明 我们想要凑出的数 不是一个整数，不可能存在任何可行方案（因为所有的物品都是整数）
        if ((targetSum + totalSum) % 2 != 0) {
            // 则：返回0
            return 0;
        }

        // 问题的等价转化
        /*
            已知：
                abs(addPlus_sum) + abs(addMinus_sum) = sum;
                abs(addPlus_sum) - abs(addMinus_sum) = target;
            => abs(addPlus_sum) = (target + sum) / 2;
         */
        // 原问题 ⇨ 0-1背包问题：
        // 使用 特定的物品选项(只能选择1次) 来 装满/凑出 容量为bagCapacity的背包，最多有 多少种装法？
        int bagCapacity = (targetSum + totalSum) / 2;
        // 如果 背包容量 小于0，说明 不存在任何可行方案
        if (bagCapacity < 0) {
            // 则：返回0
            return 0;
        }

        /* 下标i 与 数组dp[i]的定义 */
        // currentCapacityToItsSchemeAmount[j]：得到 加和结果j 存在有 dp[j]种选择方案；
        int[] currentCapacityToItsSchemeAmount = new int[bagCapacity + 1];

        /* dp[]数组元素的按需初始化 */
        currentCapacityToItsSchemeAmount[0] = 1; // 空集

        /* 正确地计算递推公式，得到所有的dp[i]元素值 */
        // 0-1背包的写法：① 先物品(Goods First)，② 后背包(Bag Second)，③ 内层循环倒序(Inner loop Reverse)
        for (int currentNum : numArr) {
            for (int currentSum = bagCapacity; currentSum >= currentNum; currentSum--) { // 背包容量递减
                /* “组合类0-1背包问题”的一般递推公式：dp[j] += dp[j-nums[i]] */
                // dp[j-nums[i]] 表示 刨掉nums[i]的空间 时，会有 多少种方案 填满 剩余空间。
                // 填满 空间j的方案数量 = 所有 刨掉当前元素后的 填满方案数量 相累加。
                currentCapacityToItsSchemeAmount[currentSum] +=
                        currentCapacityToItsSchemeAmount[currentSum - currentNum];
            }
        }

        // 从dp[]数组中 返回满足题意的元素
        return currentCapacityToItsSchemeAmount[bagCapacity];
    }
}
