package com.henry.tryout.leetcodes.Huawei.phase1.array.majority_element_169.exe;

// 如果题目没有保证 一定存在众数，则 需要在代码中 进行判断
public class Solution_Krahets_BMVotes {
    public int majorityElement(int[] nums) {
        int currentAssumeMajority = 0, itsVotes = 0;
        for (int currentNum : nums) {
            if (itsVotes == 0) {
                currentAssumeMajority = currentNum;
            }
            if (currentNum == currentAssumeMajority) {
                itsVotes += 1;
            } else {
                itsVotes += -1;
            }
        }

        // 验证 currentAssumeMajority 是否为众数
        int majorityNumCount = 0;
        for (int currentNum : nums)
            if (currentNum == currentAssumeMajority)
                majorityNumCount++;

        // 只有统计数量 大于 一半，才 作为众数返回；否则 视为 不存在众数，返回 0
        return majorityNumCount > nums.length / 2
                ? currentAssumeMajority
                : 0; // 当无众数时返回 0
    }
}
