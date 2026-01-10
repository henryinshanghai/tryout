package com.henry.tryout.leetcodes.Rakuten.possibility_to_pick_an_index_528.exe;

import java.util.Random;

public class Solution_qianwen {

    // w = [1, 3, 2]
    // prefixSum = [1, 4, 6]  → total = 6
    private int[] currentSpotToItsPrefixSum; // currentSpot -> sum of sub array end with it.
    private Random random = new Random();

    /**
     * 在构造方法中，
     *
     * @param currentSpotToItsWeightArr 权重数组
     */
    public Solution_qianwen(int[] currentSpotToItsWeightArr) {
        // 构造出 其前缀和数组 - 用于 支持对指定索引的快速定位
        currentSpotToItsPrefixSum = new int[currentSpotToItsWeightArr.length];
        currentSpotToItsPrefixSum[0] = currentSpotToItsWeightArr[0];

        for (int currentSpot = 1; currentSpot < currentSpotToItsWeightArr.length; currentSpot++) {
            currentSpotToItsPrefixSum[currentSpot] =
                    currentSpotToItsPrefixSum[currentSpot - 1] + currentSpotToItsWeightArr[currentSpot];
        }
    }

    /**
     * 根据 权重大小 来 随机返回一个索引
     *
     * @return 一个索引
     * 每个索引 对应着 一个 左闭右开的区间范围；
     */
    public int pickIndex() {
        // ① 获取到 最后一个位置 所对应的 前缀和（也就是所有权重元素的sum）
        int maxItemOfPrefixSumArr = currentSpotToItsPrefixSum[currentSpotToItsPrefixSum.length - 1];

        // ② 生成 [0, 所有元素权重sum) 之间的一个随机数
        int randomInt = random.nextInt(maxItemOfPrefixSumArr); // [0, maxItemOfPrefixSumArr)

        /* ③ 在 前缀和数组(有序数组) 中 进行 标准的upper-bound(第一个＞ target的元素的位置)的 二分查找 */
        // 找到第一个 prefixSum[i] > randomInt 的 i
        int leftBar = 0, rightBar = currentSpotToItsPrefixSum.length; // 标准写法（右边界指向length位置），而不是length-1

        // 当 左边界指针 不小于 右边界指针 时，说明 此时 左指针与右指针 都已经 指向了“bound位置”，则：退出循环
        while (leftBar < rightBar) { // ① 原则：左闭右开区间的有效性，因此 左边界指针 < 右边界指针(而不是 <=)
            int middle = (leftBar + rightBar) / 2;

            // 如果 中间位置上的元素 小于等于 该随机数，说明 middle及其左边的元素 不满足条件(>target)，则：
            if (currentSpotToItsPrefixSum[middle] <= randomInt) {
                // 更新 当前区间的左边界
                leftBar = middle + 1; // ② 按照区间定义，middle已经查看过了，所以 新区间 从middle+1开始
            } else { // 否则，说明 mid 是一个候选答案，但左边 可能还有 更小的合法索引，则：
                // 更新 当前区间的右边界
                rightBar = middle; // ③ 按照区间定义，middle作为右边界 不会被包含，所以 可以作为右边界
            }
        }

        // 返回 左/右边界指针的位置
        return leftBar;
    }
}
