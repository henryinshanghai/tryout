package com.henry.tryout.leetcodes.Huawei.phase3.dynamic_programming.climbing_stairs_70.exe;

public class Solution_qianwen_dp {
    public int climbStairs(int stairAmount) {

        if (stairAmount <= 2) {
            return stairAmount;
        }

        // ① dp[]数组及下标的含义：
        // 当前台阶编号 -> 到达该台阶的方案数量
        int[] currentStairToItsApproachAmount = new int[stairAmount + 1];

        // ③ 按照 dp[]数组的定义 以及 递推公式的依赖关系 来 对dp[]元素按需初始化
        currentStairToItsApproachAmount[1] = 1;
        currentStairToItsApproachAmount[2] = 2;

        // ④ 根据 递推公式的依赖方向 来 决定遍历顺序
        for (int currentStair = 3; currentStair <= stairAmount; currentStair++) {
            // ② 确定递推公式：dp[i] = dp[i-1] + dp[i-2]
            // 到达当前台阶的方案数量 = 到达前一个台阶的方案数量（下一步走一级台阶） + 到达前两个台阶的方案数量（下一步走两级台阶）
            currentStairToItsApproachAmount[currentStair]
                    = currentStairToItsApproachAmount[currentStair - 1] // 走到 前一个台阶的走法数量（下一步 跨一个台阶）
                    + currentStairToItsApproachAmount[currentStair - 2]; // 走到 前两个台阶的走法数量（下一步 跨两个台阶）
        }

        // 按照dp[]数组的定义，返回满足题意的dp[]元素
        // 到达 目标台阶的 走法数量
        return currentStairToItsApproachAmount[stairAmount];
    }
}
