package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.subset_90.exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_qianwen_index {
    public List<List<Integer>> subsetsWithDup(int[] numArrIncludeRepeatItem) {
        Arrays.sort(numArrIncludeRepeatItem); // ✅ 关键：排序 来 使 重复元素 相邻

        List<List<Integer>> allValidSubsets = new ArrayList<>();
        backtrack(numArrIncludeRepeatItem,
                0,
                new ArrayList<>(),
                allValidSubsets);

        return allValidSubsets;
    }

    private void backtrack(int[] sortedNumArr,
                           int startIndexAnchor,
                           List<Integer> constructingSubset,
                           List<List<Integer>> allValidSubsets) {
        // 子集问题中，递归树中的 每个节点 都是一个 有效子集（包括 空集）
        // 把 当前所构造的子集 添加到 有效子集集合中(深拷贝)
        allValidSubsets.add(new ArrayList<>(constructingSubset));

        for (int pickedNumCursor = startIndexAnchor;
             pickedNumCursor < sortedNumArr.length;
             pickedNumCursor++) {

            // ✅ 同层去重（树层去重）：跳过 重复元素
            if (pickedNumCursor > startIndexAnchor && // 同一层中的后继选择
                    sortedNumArr[pickedNumCursor] == sortedNumArr[pickedNumCursor - 1]) { // 当前元素 与 其前一个元素 相等
                // 则：跳过该重复元素（避免构造出重复的子集）
                continue;
            }

            // 选择 当前数字 来 构造子集
            constructingSubset.add(sortedNumArr[pickedNumCursor]);
            // 递归地 处理子问题（更小的可选范围）
            backtrack(sortedNumArr,
                    pickedNumCursor + 1,
                    constructingSubset,
                    allValidSubsets);
            // 回溯(撤销) 当前选择 以便 继续尝试 其他选择
            constructingSubset.remove(constructingSubset.size() - 1);
        }
    }
}
