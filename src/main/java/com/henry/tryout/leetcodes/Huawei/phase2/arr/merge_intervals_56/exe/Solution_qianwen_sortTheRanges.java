package com.henry.tryout.leetcodes.Huawei.phase2.arr.merge_intervals_56.exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution_qianwen_sortTheRanges {
    public int[][] merge(int[][] intervalArr) {
        if (intervalArr.length == 0) {
            return new int[0][0];
        }

        // Step 1: 根据 每个区间的start元素的大小 把 所有区间 按照升序排序
        Arrays.sort(intervalArr, Comparator.comparingInt(a -> a[0]));

        // Step 2: 贪心合并
        List<int[]> mergedRangeList = new ArrayList<>();

        for (int[] currentInterval : intervalArr) {
            int leftBarNum = currentInterval[0];  // 区间左边界
            int rightBarNum = currentInterval[1]; // 区间右边界

            // 如果 条件① 或 条件②，说明 当前区间是一个独立的区间
            if (mergedRangeList.isEmpty() || // 条件①：mergedRangeList 为空（可直接添加区间）
                    leftBarNum > mergedRangeList.get(mergedRangeList.size() - 1)[1]) { // 条件② 当前区间 与 列表中的最后一个区间 不重叠
                // 则：向 合并后的区间列表 中 添加 当前区间[leftBarNum, rightBarNum]
                mergedRangeList.add(new int[]{leftBarNum, rightBarNum});
            } else { // 否则，说明 当前区间 与 已合并区间列表中的最后一个区间 有重叠，则：
                // 扩展 已合并区间列表中的最后一个区间 的右边界Num 为 当前区间的右边界Num(如果它更大的话)
                mergedRangeList.get(mergedRangeList.size() - 1)[1]
                        = Math.max(
                        mergedRangeList.get(mergedRangeList.size() - 1)[1], // 原始区间的右边界
                        rightBarNum // 当前区间的右边界
                );
            }
        }

        // step3：把 列表 转化为 数组；
        // 手段：list.toArray(<passed_array>)
        return mergedRangeList.toArray(new int[mergedRangeList.size()][]);
    }
}
