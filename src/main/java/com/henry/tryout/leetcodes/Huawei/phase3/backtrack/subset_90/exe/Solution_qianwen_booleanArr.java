package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.subset_90.exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 运行耗时更少
public class Solution_qianwen_booleanArr {
    public List<List<Integer>> subsetsWithDup(int[] numArrWithDuplicateNum) {
        // 先排序 元素序列   使得重复元素相邻
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

    /**
     * 构造并收集 子集
     *
     * @param sortedNumArr                   排序好的整数序列
     * @param startIndexAnchor               当前‘可选数字范围’的起点
     * @param constructingSubset             当前所构造出的子集
     * @param spotToIfItsNumUsedInCurrSubset 位置 -> 该位置上的数字有没有在 当前子集中使用过 的映射
     * @param allValidSubsets                所有子集的集合
     */
    private void backtrack(int[] sortedNumArr,
                           int startIndexAnchor,
                           List<Integer> constructingSubset,
                           boolean[] spotToIfItsNumUsedInCurrSubset,
                           List<List<Integer>> allValidSubsets) {
        /* 收集子集（树中的每个节点） */
        allValidSubsets.add(new ArrayList<>(constructingSubset));

        /* 构造当前子集 */
        for (int pickedNumCursor = startIndexAnchor; pickedNumCursor < sortedNumArr.length; pickedNumCursor++) {
            /* 避免重复子集（手段：树层去重） */
            // ✅ 同层之间发生重复：
            // ① 整数序列中 当前元素等于其前一个，
            // ② 且 前一个元素 在当前子集中 未被使用（aka 前一个元素 在同一层的前一个树枝上 被使用过了）
            if (pickedNumCursor > 0 &&
                    sortedNumArr[pickedNumCursor] == sortedNumArr[pickedNumCursor - 1] && // 当前位置上的数字 与 其前一个位置上的数字 相同（重复数字）
                    // 如果 上一个位置的数字 在当前分支 没有被用过，
                    // 说明 它在 同层的先前分支 已经被使用过了，不能再重复使用（会产生重复子集）。
                    !spotToIfItsNumUsedInCurrSubset[pickedNumCursor - 1]) {
                // 则：跳过 对该数字的处理（必须做的剪枝）
                continue;
            }

            /* 为 当前子集 选择 当前位置上的数字 */
            constructingSubset.add(sortedNumArr[pickedNumCursor]);
            // 记录该数字已经 在当前子集中被使用了
            spotToIfItsNumUsedInCurrSubset[pickedNumCursor] = true;

            // 递归地 继续构造 当前子集
            backtrack(sortedNumArr,
                    pickedNumCursor + 1,
                    constructingSubset,
                    spotToIfItsNumUsedInCurrSubset,
                    allValidSubsets);

            // 撤销为当前子集 所选择的数字，以便 继续尝试其他数字
            constructingSubset.remove(constructingSubset.size() - 1);
            spotToIfItsNumUsedInCurrSubset[pickedNumCursor] = false;
        }
    }
}
