package com.henry.tryout.leetcodes.Rakuten.missing_number_41.exe;

public class Solution_qianwen_exchange {
    public int firstMissingPositive(int[] nums) {
        int numAmount = nums.length;

        for (int currentSpot = 0; currentSpot < numAmount; currentSpot++) {

            // 只要 nums[currentSpot] 是 [1, numAmount] 范围内的数，
            // 且它 不在 正确的位置 上，就 不断尝试 把 它 放到 该放的位置。
            // 直到 当前位置上放置了正确的数字 or 发现 当前位置上的数字 重复了
            // 🐖 交换过来的元素 可能 仍旧 不在其正确的位置上，所以 这里使用 while 而不是 if
            while (nums[currentSpot] >= 1 && nums[currentSpot] <= numAmount
                    && numIsNotOnCorrectSpot(nums, currentSpot)) {

                // 交换 nums[currentSpot] 和 nums[nums[currentSpot] - 1]
                arrangeNumOn(nums, currentSpot);
            }
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
        int temp = nums[nums[currentSpot] - 1];
        nums[nums[currentSpot] - 1] = nums[currentSpot];
        nums[currentSpot] = temp;
    }

    private boolean numIsNotOnCorrectSpot(int[] nums, int currentSpot) {
        int numOnCurrentSpot = nums[currentSpot];
        int numsExpectedSpot = numOnCurrentSpot - 1;

        // 在 期待出现指定元素的位置上 的元素 不是 该元素
        return nums[numsExpectedSpot] != numOnCurrentSpot;
    }
}
