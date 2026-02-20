package com.henry.tryout.leetcodes.Rakuten.missing_number_41.exe;

// 目标：标记 有效数字的存在性
// 手段：对于一个有效数字，我们把 它所对应位置上的数字 设置为负数；
// 示例：数字5；数字5所对应的位置：4；位置4上的原始数字：100；标识‘5的存在性’后位置4上的数字：-100；
public class Solution_qianwen_markAsNegative {
    public int firstMissingPositive(int[] nums) {
        int numAmount = nums.length;

        // ==============================
        // ① 先 清除所有的“无效数字”
        // ==============================
        // 目标：将所有 ≤0 或 >numAmount 的数字替换为一个“无害”的正数（如 numAmount+1）
        // 原因：这些数字不在 有效范围[1, numAmount]内，对答案没有影响（答案一定在 [1, numAmount+1]）
        for (int currentNumCursor = 0; currentNumCursor < numAmount; currentNumCursor++) {
            if (nums[currentNumCursor] <= 0 || nums[currentNumCursor] > numAmount) {
                nums[currentNumCursor] = numAmount + 1; // 替换为 不会干扰后续标记 的值
            }
        }

        // ==============================
        // ② 再 对当前数字，（原地）标记其存在性
        // 副作用：这会改变原始的数字序列
        // ==============================
        // 核心思想：用下标 i 表示数字 (i+1) 是否出现过
        // 如果数字 x 出现了，就把 nums[x - 1] 变成负数（作为标记）
        for (int currentNumCursor = 0; currentNumCursor < numAmount; currentNumCursor++) {
            // 获取到原始的元素值，
            // 手段：对 当前元素 进行abs()的操作
            // 原因：原始元素 在先前的循环中 可能 已经被标记为 负数（副作用）
            // 例子：位置2上的数字5 会把 位置4上的元素100 修改为 -100；
            int originalCurrentNum = Math.abs(nums[currentNumCursor]);

            // 因为 👆 已处理，originalCurrentNum 理论上 ∈ [1, numAmount+1]
            // 我们只关心 [1, numAmount] 范围内的数
            if (originalCurrentNum <= numAmount) {
                // 标识 当前元素的 存在性
                // 手段：将 其排定位置的元素 变为 负数（表示数字 originalCurrentNum 出现过）
                // 使用 -Math.abs(...) 确保 结果为负，避免 负负得正
                int itsArrangedSpot = originalCurrentNum - 1;

                nums[itsArrangedSpot] = -Math.abs(nums[itsArrangedSpot]);
            }
        }

        // ==============================
        // ③ 最后 检查缺失的数字
        // ==============================
        // 遍历数组，找到第一个 仍为正数的位置 i
        // 说明数字 (i + 1) 没有出现过 → 它就是答案
        for (int currentNumCursor = 0; currentNumCursor < numAmount; currentNumCursor++) {
            if (nums[currentNumCursor] > 0) {
                int missingNum = currentNumCursor + 1;
                return missingNum;
            }
        }

        // ==============================
        // ④ 如果所有 1～numAmount 都出现了
        // ==============================
        // 说明此时 最小缺失正整数 正是 numAmount + 1
        return numAmount + 1;
    }
}
