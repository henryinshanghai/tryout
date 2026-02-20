package com.henry.tryout.leetcodes.Rakuten.similar_strs_group_839.exe;

import java.util.ArrayList;
import java.util.List;

// 推荐 把 UF 单独实现成 一个类
public class Solution_qianwen_UF {
    public int numSimilarGroups(String[] strs) {
        int strAmount = strs.length;

        // 创建一个 unionFind对象
        // 初始化 其中组（节点）的数量 为 字符串的数量
        UnionFind forest = new UnionFind(strAmount);

        for (int currentAnchorSpot = 0; currentAnchorSpot < strAmount; currentAnchorSpot++) {
            for (int currentCursorSpot = currentAnchorSpot + 1; currentCursorSpot < strAmount; currentCursorSpot++) {
                /* 把 指向字符串的指针位置 作为森林中的节点 */
                // 如果 这两个节点 已经属于 同一棵树，说明 当前的两个字符串 已经位于同一个组中，
                if (forest.isBelongToSameComponent(currentAnchorSpot, currentCursorSpot)) {
                    // 则：跳过连接
                    continue;
                }

                // 如果 当前指针所指向的 两个字符串 相似，说明 它们应该是连通的，
                if (isSimilar(strs[currentAnchorSpot], strs[currentCursorSpot])) {
                    // 则：把 它们 合并到 同一个组中
                    forest.unionToSameComponent(currentAnchorSpot, currentCursorSpot);
                }
            }
        }

        // 返回 最终的unionFind对象中 所有组的数量
        return forest.getCount();
    }

    boolean isSimilar(String str1, String str2) {
        // 先判断 字符串 是否相等
        if (str1.equals(str2))
            return true;

        // 统计 两个字符串 所存在的 在相同位置上字符不同的 位置数量
        List<Integer> differentCharSpots = new ArrayList<>();
        for (int currentSpot = 0; currentSpot < str1.length(); currentSpot++) {
            if (str1.charAt(currentSpot) != str2.charAt(currentSpot)) {
                differentCharSpots.add(currentSpot);

                if (differentCharSpots.size() > 2) {
                    return false;
                }
            }
        }

        // 根据 相似性的定义 判断 是否相似
        return differentCharSpots.size() == 2;
    }
}