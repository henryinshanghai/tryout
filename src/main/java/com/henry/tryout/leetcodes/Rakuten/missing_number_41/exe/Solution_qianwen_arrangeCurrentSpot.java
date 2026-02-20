package com.henry.tryout.leetcodes.Rakuten.missing_number_41.exe;

// 概念：
// 〇 排定位置：为 给定的位置 放上 其正确的数字；
// ① 预期数字：对于 位置currentSpot，其预期数字为(currentSpot+1)
// ② 重复数字：当前数字的排定位置 上的数字 = 当前数字，则 当前数字 是一个重复数字；
class Solution_qianwen_arrangeCurrentSpot {
    public int firstMissingPositive(int[] nums) {

        // 预处理数组，使得 currentSpot上的数字 = {预期数字, 无效数字, 重复数字}；
        // 手段：对于每一个当前位置，尝试排定该位置（交换法）
        for (int currentSpot = 0; currentSpot < nums.length; currentSpot++) {

            // 对于 当前位置，尝试排定 该位置。
            // 直到 当前位置 为预期数字（被排定） 或者 无效数字 或者 重复数字
            while (nums[currentSpot] >= 1 &&
                    nums[currentSpot] <= nums.length && // 有效数字
                    nums[currentSpot] != currentSpot + 1) { // ‘当前位置’上的数字 不是 预期数字
                // 把 当前位置上的数字 交换到 它的排定位置上去
                int itsArrangedSpot = nums[currentSpot] - 1;

                // 🐖 如果 其排定位置上 已经是 当前数字 了，说明 出现了重复数字，
                if (nums[itsArrangedSpot] == nums[currentSpot]) {
                    // 则：不再继续排定 ‘当前位置上的数字’，直接break
                    break;
                }

                int temp = nums[itsArrangedSpot];
                nums[itsArrangedSpot] = nums[currentSpot];
                nums[currentSpot] = temp;
            } /* 循环结束后，当前位置 要么被排定、要么是无效数字 或 重复数字 */
        }

        // 查看 各个位置上的数字 是否是 预期数字
        for (int currentNumCursor = 0; currentNumCursor < nums.length; currentNumCursor++) {
            if (nums[currentNumCursor] != currentNumCursor + 1) {
                return currentNumCursor + 1;
            }
        }

        // 所有位置上 都是 预期数字，说明 第一个缺少的数字 是 长度+1
        return nums.length + 1;
    }
}
