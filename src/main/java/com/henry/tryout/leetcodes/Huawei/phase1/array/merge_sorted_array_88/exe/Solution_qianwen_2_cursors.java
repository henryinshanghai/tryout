package com.henry.tryout.leetcodes.Huawei.phase1.array.merge_sorted_array_88.exe;

public class Solution_qianwen_2_cursors {
    public void mergeNumsIn(int[] numArr1,
                            int validNumAmountInArr1,
                            int[] numArr2,
                            int validNumAmountInArr2) {
        // 设置三个指针
        int backwardCursorOfNumToCompareInArr1 = validNumAmountInArr1 - 1;     // nums1 有效部分的末尾
        int backwardCursorOfNumToCompareInArr2 = validNumAmountInArr2 - 1;     // nums2 的末尾
        int backwardsCursorOfSpotToFill = validNumAmountInArr1 + validNumAmountInArr2 - 1;  // nums1 整体的末尾（目标位置）

        // 从后往前 合并
        while (backwardCursorOfNumToCompareInArr1 >= 0 && backwardCursorOfNumToCompareInArr2 >= 0) {
            // 选择 待比较元素中较大的元素 放到 backwardsCursorOfSpotToFill 位置
            if (numArr1[backwardCursorOfNumToCompareInArr1] > numArr2[backwardCursorOfNumToCompareInArr2]) {
                // 放置 该较大元素 到 指针指向的位置
                numArr1[backwardsCursorOfSpotToFill] = numArr1[backwardCursorOfNumToCompareInArr1];
                // 把 所使用的 待比较元素的指针 向前移动一个位置
                backwardCursorOfNumToCompareInArr1--;
            } else {
                // 放置 该较大元素 到 指针指向的位置
                numArr1[backwardsCursorOfSpotToFill] = numArr2[backwardCursorOfNumToCompareInArr2];
                // 把 所使用的 待比较元素的指针 向前移动一个位置
                backwardCursorOfNumToCompareInArr2--;
            }

            // 把 待填充（排定）位置的指针 向前移动一个位置
            backwardsCursorOfSpotToFill--;
        }

        // 如果 nums2 还有剩余元素（nums1 已处理完）
        // 将 nums2中所有剩余元素 逐一复制到 nums1数组 开头（nums1数组中的有效元素 都已经排定了）
        while (backwardCursorOfNumToCompareInArr2 >= 0) {
            numArr1[backwardsCursorOfSpotToFill] = numArr2[backwardCursorOfNumToCompareInArr2];
            backwardsCursorOfSpotToFill--;
            backwardCursorOfNumToCompareInArr2--;
        }

        // 注意：如果 nums1 有剩余，它们已经在正确位置，无需操作
    }
}
