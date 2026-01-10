package com.henry.tryout.leetcodes.Rakuten.existed_longest_seq_128.exe;

import java.util.HashSet;
import java.util.Set;

public class Solution_lingshancha {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num); // 把 nums 转成哈希集合
        }

        int maxSeqLength = 0;
        // 🐖 遍历哈希集合
        for (int currentNum : numSet) {
            // 如果 currentNum 不是序列的起点，直接跳过
            if (numSet.contains(currentNum - 1)) {
                continue;
            }

            // currentNum 是序列的起点
            int nextNumInSeq = currentNum + 1;
            while (numSet.contains(nextNumInSeq)) { // 不断查找下一个数是否在哈希集合中
                nextNumInSeq++;
            }

            // while循环结束后，nextNumInSeq-1 是 最后一个 在哈希集合中的数
            maxSeqLength = Math.max(maxSeqLength, nextNumInSeq - currentNum); // 从 currentNum 到 nextNumInSeq-1 一共 nextNumInSeq-currentNum 个数
        }
        return maxSeqLength;
    }
}
