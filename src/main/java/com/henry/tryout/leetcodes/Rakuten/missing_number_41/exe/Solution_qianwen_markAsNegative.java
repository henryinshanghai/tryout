package com.henry.tryout.leetcodes.Rakuten.missing_number_41.exe;

public class Solution_qianwen_markAsNegative {
    public int firstMissingPositive(int[] nums) {
        int numAmount = nums.length;

        // ==============================
        // Step 1: 预处理 —— 清除“无效数字”
        // ==============================
        // 目标：将所有 ≤0 或 >numAmount 的数字替换为一个“无害”的正数（如 numAmount+1）
        // 原因：这些数字不在 有效范围[1, numAmount]内，对答案没有影响（答案一定在 [1, numAmount+1]）
        for (int currentSpot = 0; currentSpot < numAmount; currentSpot++) {
            if (nums[currentSpot] <= 0 || nums[currentSpot] > numAmount) {
                nums[currentSpot] = numAmount + 1; // 替换为 不会干扰后续标记 的值
            }
        }

        // ==============================
        // Step 2: 原地标记 —— 利用 负号 表示 “存在”
        // ==============================
        // 核心思想：用下标 i 表示数字 (i+1) 是否出现过
        // 如果数字 x 出现了，就把 nums[x - 1] 变成负数（作为标记）
        for (int currentSpot = 0; currentSpot < numAmount; currentSpot++) {
            int absOfNumOnCurrentSpot = Math.abs(nums[currentSpot]); // 取绝对值：防止 之前 已被标记为 负数

            // 因为 Step 1 已处理，absOfNumOnCurrentSpot 理论上 ∈ [1, numAmount+1]
            // 我们只关心 [1, numAmount] 范围内的数
            if (absOfNumOnCurrentSpot <= numAmount) {
                // 标识 当前元素的 存在性
                // 手段：将 其对应位置的元素 变为 负数（表示数字 absOfNumOnCurrentSpot 出现过）
                // 使用 -Math.abs(...) 确保 结果为负，避免 负负得正
                int numHashedSpot = absOfNumOnCurrentSpot - 1;

                nums[numHashedSpot] = -Math.abs(nums[numHashedSpot]);
            }
        }

        // ==============================
        // Step 3: 查找第一个 未被标记的位置
        // ==============================
        // 遍历数组，找到第一个 仍为正数的位置 i
        // 说明数字 (i + 1) 没有出现过 → 它就是答案
        for (int currentSpot = 0; currentSpot < numAmount; currentSpot++) {
            if (nums[currentSpot] > 0) {
                int missingNum = currentSpot + 1;
                return missingNum;
            }
        }

        // ==============================
        // Step 4: 所有 1～numAmount 都出现了
        // ==============================
        // 此时 最小缺失正整数 是 numAmount + 1
        return numAmount + 1;
    }
}
