package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.permutations_46.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_backtrack {
    public List<List<Integer>> permute(int[] numArr) {

        List<List<Integer>> allPermutationList = new ArrayList<>();
        List<Integer> constructedPermutation = new ArrayList<>();

        // 准备一个标记数组     用于标记 该位置上的元素 是否已经被用于 构造当前排列
        boolean[] spotToIfItsNumBeenUsedInCurrPermu = new boolean[numArr.length];

        backtrack(numArr,
                constructedPermutation,
                spotToIfItsNumBeenUsedInCurrPermu,
                allPermutationList);

        return allPermutationList;
    }

    /**
     * 回溯 构造出 全排列
     * @param numArr   原始数组（无重复元素）
     * @param constructingPermutation   当前已选择的元素列表（部分排列）
     * @param spotToIfItsNumBeenUsedInCurrPermu   used[i] 表示 nums[i] 是否已被选入 path
     * @param allPermutationList 存储所有完整排列的结果列表
     */
    private void backtrack(int[] numArr,
                           List<Integer> constructingPermutation,
                           boolean[] spotToIfItsNumBeenUsedInCurrPermu,
                           List<List<Integer>> allPermutationList) {
        /* 构造过程结束 */
        // 🛑 终止条件：
        // 如果 path 长度等于 nums 长度，说明 找到一个完整排列，
        if (constructingPermutation.size() == numArr.length) {
            // 则：把 构造出的排列 添加到 结果集中
            // 🐖 必须深拷贝！
            allPermutationList.add(new ArrayList<>(constructingPermutation));
            // 返回 上一级调用
            return;
        }

        /* 构造出 全排列 */
        // 🐖（排列问题 必须从 0 开始遍历）
        for (int currentNumSpot = 0; currentNumSpot < numArr.length; currentNumSpot++) {
            // 剪枝：（在选择当前数字之前）判断该数字 是否 已经在当前排列中 使用过了
            if (spotToIfItsNumBeenUsedInCurrPermu[currentNumSpot]) {
                continue;
            }

            /* 为 当前排列的当前位置 选择 当前数字 */
            // 手段：将 nums[currentNumSpot] 加入 path，并 标记为 已使用
            constructingPermutation.add(numArr[currentNumSpot]);
            spotToIfItsNumBeenUsedInCurrPermu[currentNumSpot] = true;

            // 递归地 继续构造 全排列
            backtrack(numArr,
                    constructingPermutation,
                    spotToIfItsNumBeenUsedInCurrPermu,
                    allPermutationList);

            /* 为 当前排列的当前位置 撤销 当前数字（以便继续尝试 新的数字） */
            constructingPermutation.remove(constructingPermutation.size() - 1);
            spotToIfItsNumBeenUsedInCurrPermu[currentNumSpot] = false;
        }
    }
}
