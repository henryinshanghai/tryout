package com.henry.tryout.leetcodes.Rakuten.possibility_to_pick_an_index_528.exe;

import java.util.Random;

public class Solution_tongge {
    private static Random random = new Random();
    private int[] currentIndexToItsPrefixSum;

    public Solution_tongge(int[] currentIndexToItsWeight) {
        this.currentIndexToItsPrefixSum = new int[currentIndexToItsWeight.length];
        this.currentIndexToItsPrefixSum[0] = currentIndexToItsWeight[0];


        for (int currentIndex = 1; currentIndex < currentIndexToItsWeight.length; currentIndex++) {
            this.currentIndexToItsPrefixSum[currentIndex] =
                    this.currentIndexToItsPrefixSum[currentIndex - 1] + currentIndexToItsWeight[currentIndex];
        }
    }

    public int pickIndex() {
        int max = this.currentIndexToItsPrefixSum[this.currentIndexToItsPrefixSum.length - 1];
        int rand = random.nextInt(max) + 1;

        return binarySearch(currentIndexToItsPrefixSum, rand);
    }

    /**
     * upper-bound类型的二分查找
     * 从一个 指定的有序数组中，查找 满足 指定条件 的边界/插入位置
     *
     * @param sortedNumSeq  指定的有序数组
     * @param target  指定的条件 - 找第一个 ≥ / > target 的位置
     * @return
     */
    private int binarySearch(int[] sortedNumSeq, int target) {
        int leftBar = 0, rightBar = sortedNumSeq.length - 1;

        while (leftBar < rightBar) {
            int middle = (leftBar + rightBar) / 2;

            // 考虑等于的情况，去掉等号过不了所有用例
            if (target <= sortedNumSeq[middle]) {
                rightBar = middle;
            } else {
                leftBar = middle + 1;
            }
        }

        return rightBar;
    }
}
