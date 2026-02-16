package com.henry.tryout.leetcodes.Rakuten.existed_longest_seq_128.exe;

import java.util.HashSet;
import java.util.Set;

// 不同点：
// ① 不使用 一个额外的变量 来 累计序列的长度，而是 直接计算出来
// ② 使用卫语句的写法，对于 不能作为序列起点的元素，直接跳过
public class Solution_lingshan_calculateSeqLength {
    public int longestConsecutive(int[] nums) {
        // 把 nums 转成 哈希集合
        // ① 去重；② 快速判断 指定元素 是否存在；
        Set<Integer> numSet = new HashSet<>();
        for (int currentNum : nums) {
            numSet.add(currentNum);
        }

        int maxSeqLength = 0;
        // 🐖 遍历哈希集合
        for (int currentNum : numSet) {
            // 如果 currentNum 无法作为 连续序列的起点，
            if (numSet.contains(currentNum - 1)) {
                // 则：直接跳过
                continue;
            }

            // currentNum 是 序列的起始元素
            int nextNumInSeq = currentNum + 1;
            // 不断查找 nextNumInSeq 是否存在于哈希集合中
            while (numSet.contains(nextNumInSeq)) {
                nextNumInSeq++;
            } /* while循环结束后，nextNumInSeq-1 是 连续序列中的最后一个元素 */

            maxSeqLength =
                    Math.max(maxSeqLength,
                            nextNumInSeq - currentNum); // ’当前连续序列‘的长度
        }

        return maxSeqLength;
    }
}
