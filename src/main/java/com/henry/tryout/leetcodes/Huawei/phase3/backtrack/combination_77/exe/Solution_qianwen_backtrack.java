package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.combination_77.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_backtrack {
    List<List<Integer>> allValidCombiList = new ArrayList<>();
    List<Integer> currValidCombi = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k);
        return allValidCombiList;
    }

    /**
     * 构造出 满足条件的组合
     *
     * @param currStartIndex 当前层从哪个数字开始选 (保证不重复、有序)
     * @param n              上限
     * @param k              目标长度
     */
    private void backtrack(int currStartIndex, int n, int k) {
        /* 构造过程结束 */
        // 终止条件：所构造的组合中的元素数量（路径长度）达到 k
        if (currValidCombi.size() == k) {
            // 🐖：这里一定要 new一个新的副本，否则 存的是引用
            allValidCombiList.add(new ArrayList<>(currValidCombi));
            return;
        }

        /* 构造 符合条件的组合 */
        // 剪枝依据：剩余可选的数字的数量（n - currNumCursor + 1） >= 当前组合还需要的数字的数量（k - currValidCombi.size()）
        for (int currentNumCursor = currStartIndex; currentNumCursor <= n - (k - currValidCombi.size()) + 1; currentNumCursor++) {
            // 为 当前组合 选择 当前数字
            currValidCombi.add(currentNumCursor);

            // 继续 递归地构造 当前组合
            // 🐖：下一层构造 可选数字的范围 从currentNumCursor+1开始，保证不会构造出 重复组合
            backtrack(currentNumCursor + 1, n, k);

            // 撤销 当前组合中的当前数字
            currValidCombi.remove(currValidCombi.size() - 1);
        }
    }
}
