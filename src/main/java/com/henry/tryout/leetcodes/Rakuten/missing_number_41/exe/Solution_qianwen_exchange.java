package com.henry.tryout.leetcodes.Rakuten.missing_number_41.exe;

public class Solution_qianwen_exchange {
    public int firstMissingPositive(int[] nums) {
        int numAmount = nums.length;

        for (int currentSpot = 0; currentSpot < numAmount; currentSpot++) {

            // 只要 ① 当前元素nums[currentSpot] 是 [1, numAmount] 范围内的数，
            // ② 且它 不在 正确的位置 上...
            while (nums[currentSpot] >= 1 && nums[currentSpot] <= numAmount
                    && numIsNotOnCorrectSpot(nums, currentSpot)) {

                // 就 不断尝试 把 它 排定到 该放的位置
                // 手段：交换 nums[currentSpot] 和 nums[nums[currentSpot] - 1]
                arrangeNumOn(nums, currentSpot);
            }
            /* 循环结束时，当前位置上的元素：
                要么 是(currentSpot+1)[break 条件②]，
                要么是 无效数字(<=0, >=n[条件①], 重复的无效数字[条件②])；
            所有 能被排定的数字 都 已被排定 */
        }

        // 找 第一个 不匹配的位置
        for (int currentSpot = 0; currentSpot < numAmount; currentSpot++) {
            // 如果 当前位置上的元素 不是 预期的元素，说明 (currentSpot + 1) 就是 所缺失的正整数，
            if (notHavingExpectedNum(nums, currentSpot)) {
                // 则：返回它
                int firstMissingPositive = currentSpot + 1;
                return firstMissingPositive;
            }
        }

        // 如果 所有位置上 都被放置了 预期的元素，说明 没有出现的最小正整数 是 (numAmount + 1)
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
        int numOnCurrentSpot = nums[currentSpot];
        int numsExpectedSpot = numOnCurrentSpot - 1;

        // 在 期待出现指定元素的位置上 的元素 不是 该元素
        return nums[numsExpectedSpot] != numOnCurrentSpot;
    }
}
