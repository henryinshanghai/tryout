package com.henry.tryout.leetcodes.Huawei.phase1.array.majority_element_169.exe;

public class Solution_qianwen_BoyerMooreVote {
    public int majorityElement(int[] nums) {
        int currentAssumeMajority = nums[0];
        int itsVotes = 1;

        for (int currentNumSpot = 1; currentNumSpot < nums.length; currentNumSpot++) {
            // 如果 当前元素 与 当前的‘多数元素’ 相等，则：
            if (nums[currentNumSpot] == currentAssumeMajority) {
                // 其 票数+1
                itsVotes++;
            } else { // 如果 不相等，则：
                // 其 票数-1
                itsVotes--;

                // 如果 当前'多数元素'的投票结果 变成了 0，说明 它不是 真正的‘多数元素’，则：
                if (itsVotes == 0) {
                    // 使用 当前元素 作为 新的‘多数元素’
                    currentAssumeMajority = nums[currentNumSpot];
                    // 并 重置 ‘多数元素’的计数器为 1
                    itsVotes = 1;
                }
            }
        }

        // 题目 保证存在 多数元素，因此无需验证
        return currentAssumeMajority;
    }
}
