package com.henry.tryout.leetcodes.Rakuten.similar_strs_group_839.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen {
    public int numSimilarGroups(String[] strs) {
        int strAmount = strs.length;

        // 创建一个 unionFind对象 - 初始化其中组的数量为 strAmount
        UnionFind forest = new UnionFind(strAmount);

        for (int currentSpotI = 0; currentSpotI < strAmount; currentSpotI++) {
            for (int currentSpotJ = currentSpotI + 1; currentSpotJ < strAmount; currentSpotJ++) {
                if(forest.isBelongToSameComponent(currentSpotI, currentSpotJ)) {
                    continue;
                }

                // 如果 指针所指向的 两个字符串相似，则：
                if (isSimilar(strs[currentSpotI], strs[currentSpotJ])) {
                    // 把 它们 合并到 同一个组中
                    forest.unionToSameComponent(currentSpotI, currentSpotJ);
                }
            }
        }

        // 返回 最终的unionFind对象中 所有组的数量
        return forest.getCount();
    }

    boolean isSimilar(String str1, String str2) {
        // 先判断 字符串是否相等
        if (str1.equals(str2))
            return true;

        // 统计 两个字符串 所存在的 在相同位置上字符不同的个数
        List<Integer> differentCharSpots = new ArrayList<>();
        for (int currentSpot = 0; currentSpot < str1.length(); currentSpot++) {
            if (str1.charAt(currentSpot) != str2.charAt(currentSpot)) {
                differentCharSpots.add(currentSpot);
            }
        }

        // 根据相似性的定义 判断 是否相似
        return isSimilar(str1, str2, differentCharSpots);
    }

    private boolean isSimilar(String str1, String str2, List<Integer> differentCharSpots) {
        /* str1与str2必然是 同构异位词，因此 只需要判断 字符不同的位置 是不是 只有两个 */
        char firstDifferentCharInStr1 = str1.charAt(differentCharSpots.get(0));
        char secondDifferentCharInStr2 = str2.charAt(differentCharSpots.get(1));


        char secondDifferentCharInStr1 = str1.charAt(differentCharSpots.get(1));
        char firstDifferentCharInStr2 = str2.charAt(differentCharSpots.get(0));

        return differentCharSpots.size() == 2;
//                && firstDifferentCharInStr1 == secondDifferentCharInStr2
//                && secondDifferentCharInStr1 == firstDifferentCharInStr2;
    }
}

class UnionFind {
    private int[] currentNodeToItsParentNode;
    private int treeAmount;

    public UnionFind(int nodeAmount) {
        // 容量初始化
        currentNodeToItsParentNode = new int[nodeAmount];

        // 图中树的数量的初始化   - 最开始时，所有节点 都是一棵 独立的树
        treeAmount = nodeAmount;

        // 初始时，每个节点的父节点 都是 它自己
        for (int currentNode = 0; currentNode < nodeAmount; currentNode++) {
            currentNodeToItsParentNode[currentNode] = currentNode;
        }
    }

    public boolean isBelongToSameComponent(int nodeI, int nodeJ) {
        int componentIdOfNodeI = findComponentIdOf(nodeI);
        int componentIdOfNodeJ = findComponentIdOf(nodeJ);

        return componentIdOfNodeI == componentIdOfNodeJ;
    }

    /**
     * 找到 指定节点 所属分组的 组ID
     * @param currentNode   指定的节点
     * @return
     */
    public int findComponentIdOf(int currentNode) {
        // 如果 当前节点的父节点 不是 它自己，说明 它还不是根节点，则：
        if (currentNodeToItsParentNode[currentNode] != currentNode) {
            // 递归地 查找其父节点的根节点，并 进行路径压缩
            currentNodeToItsParentNode[currentNode] = findComponentIdOf(currentNodeToItsParentNode[currentNode]); // 路径压缩
        }

        // 路径压缩后，树 变成了 一个只有两层的树 - 所有节点的父节点 都是 根节点
        return currentNodeToItsParentNode[currentNode];
    }

    /**
     * 把 两个指定的节点 连接起来
     * @param node1
     * @param node2
     */
    public void unionToSameComponent(int node1, int node2) {
        int rootOfNode1 = findComponentIdOf(node1);
        int rootOfNode2 = findComponentIdOf(node2);

        // 如果 两个节点的根节点 不相同，说明 它们 现在还不在同一个连通分量中，
        if (rootOfNode1 != rootOfNode2) {
            // 则：把 这两个连通分量 合并成一个连通分量
            // 手段：重置 其中一棵树的根节点的父节点 为 另一颗树的根节点
            currentNodeToItsParentNode[rootOfNode1] = rootOfNode2;
            // 合并后，树(连通分量)的数量减一
            treeAmount--;
        }
    }

    // 获取到 图中所有的连通分量 的数量
    // 🐖 这个API需要 在所有union操作完成之后 使用
    public int getCount() {
        return treeAmount;
    }
}