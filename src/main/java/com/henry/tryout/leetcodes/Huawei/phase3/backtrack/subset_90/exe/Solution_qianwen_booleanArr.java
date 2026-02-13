package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.subset_90.exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_qianwen_booleanArr {
    public List<List<Integer>> subsetsWithDup(int[] numArrWithDuplicateNum) {
        // 排序元素序列   使得重复元素相邻
        Arrays.sort(numArrWithDuplicateNum);
        // 当前位置 -> 在构造当前子集时，该位置上的元素是否已经被使用 的映射
        boolean[] spotToItsNumUsedInCurrSubset = new boolean[numArrWithDuplicateNum.length];
        // 列表的列表 用于存储 所有的子集
        List<List<Integer>> allValidSubsets = new ArrayList<>();

        backtrack(numArrWithDuplicateNum,
                0,
                new ArrayList<>(),
                spotToItsNumUsedInCurrSubset,
                allValidSubsets);

        return allValidSubsets;
    }

    private void backtrack(int[] sortedNumArr,
                           int startIndexAnchor,
                           List<Integer> constructingSubset,
                           boolean[] spotToIfItsNumUsedInCurrSubset,
                           List<List<Integer>> allValidSubsets) {

        allValidSubsets.add(new ArrayList<>(constructingSubset));

        for (int pickedNumCursor = startIndexAnchor; pickedNumCursor < sortedNumArr.length; pickedNumCursor++) {
            // ✅ 同层去重：当前元素等于前一个，且 前一个元素 在当前子集中 未被使用（说明是同层）
            if (pickedNumCursor > 0 &&
                    sortedNumArr[pickedNumCursor] == sortedNumArr[pickedNumCursor - 1] && // 当前位置上的数字 与 其前一个位置上的数字 相同（重复数字）
                    // 如果 上一个位置的数字 在当前分支 没有被用过，说明 它在 同层的先前分支 已经被使用过了，不能再重复使用（会产生重复子集）。则：
                    !spotToIfItsNumUsedInCurrSubset[pickedNumCursor - 1]) {
                // 直接剪掉 当前分支
                continue;
            }

            // 为 当前子集 选择 当前位置上的数字
            spotToIfItsNumUsedInCurrSubset[pickedNumCursor] = true;
            constructingSubset.add(sortedNumArr[pickedNumCursor]);

            // 递归地 完成子问题（更少的可选数字）
            backtrack(sortedNumArr,
                    pickedNumCursor + 1,
                    constructingSubset,
                    spotToIfItsNumUsedInCurrSubset,
                    allValidSubsets);

            // 回溯（撤销）所选择的数字，以便 继续尝试其他数字
            constructingSubset.remove(constructingSubset.size() - 1);
            spotToIfItsNumUsedInCurrSubset[pickedNumCursor] = false;
        }
    }
}
