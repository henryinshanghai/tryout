package com.henry.tryout.leetcodes.Rakuten.missing_number_41.exe;

// 目标：标记 有效数字的存在性
// 手段：当前位置 -> 该位置上的预期数字
// 概念：
// 〇 排定数字：把 给定的数字 放到 其正确的位置上；
// ① 预期数字：位置0上的预期数字为1，位置1上的预期数字为2...位置currentSpot上的预期数字为(currentSpot+1)
// ② 有效数字：在闭区间[1, nums.length]之间的数字；
// ③ 重复数字：当前数字 与 当前数字的排定位置上的数字 相等；
public class Solution_qianwen_arrangeCurrentNum {
    public int firstMissingPositive(int[] nums) {
        int numAmount = nums.length;

        // 预处理数组，使得 currentSpot上的数字 = {预期数字, 无效数字, 重复数字}；
        // 手段：对于每一个 当前位置上的数字，尝试排定 该数字（交换法）
        for (int currentSpot = 0; currentSpot < numAmount; currentSpot++) {

            // 对于 当前位置上的数字，尝试排定该数字。
            // 直到 当前位置 为预期数字（被排定） 或者 无效数字 或者 重复数字
            while (nums[currentSpot] >= 1 &&
                    nums[currentSpot] <= numAmount && // ① 有效数字
                    numIsNotOnCorrectSpot(nums, currentSpot)) { // ② 当前数字的排定位置上 还不是 当前数字

                // 尝试 把 它 放到 其排定位置上
                // 手段：交换 nums[currentSpot] 和 nums[nums[currentSpot] - 1]
                arrangeNumOn(nums, currentSpot);
            }
            /* 循环结束时，当前位置上的数字：
                要么 是(currentSpot+1)[预期数字]，
                要么是 无效数字（条件①）或是 重复数字（条件②）；
            所有 能被排定的数字 都 已被排定 */
        }

        // 找 第一个 其上不是‘预期数字’的位置
        for (int currentSpot = 0; currentSpot < numAmount; currentSpot++) {
            // 如果 当前位置上的元素 不是 预期的元素，
            // 说明 (currentSpot + 1) 就是 所缺失的正整数，
            if (notHavingExpectedNum(nums, currentSpot)) {
                // 则：返回它
                int firstMissingPositive = currentSpot + 1;
                return firstMissingPositive;
            }
        }

        // 如果 所有位置上 都被放置了 预期数字，
        // 说明 没有出现的最小正整数 正是 (numAmount + 1)
        return numAmount + 1;
    }

    private boolean notHavingExpectedNum(int[] nums, int currentSpot) {
        int expectedNum = currentSpot + 1;

        // 当前位置上的元素 不等于 预期元素
        return nums[currentSpot] != expectedNum;
    }

    private void arrangeNumOn(int[] nums, int currentSpot) {
        int shouldArrangedSpot = nums[currentSpot] - 1;

        int temp = nums[shouldArrangedSpot];
        nums[shouldArrangedSpot] = nums[currentSpot];
        nums[currentSpot] = temp;
    }

    private boolean numIsNotOnCorrectSpot(int[] nums, int currentSpot) {
        // 获取到 当前位置上的数字
        int numOnCurrentSpot = nums[currentSpot];
        // 计算出 该数字 应该呆的位置
        int itsArrangedSpot = numOnCurrentSpot - 1;

        // 检查 在 应该呆的位置上 的数字 是不是 该数字
        return nums[itsArrangedSpot] != numOnCurrentSpot;
    }
}
