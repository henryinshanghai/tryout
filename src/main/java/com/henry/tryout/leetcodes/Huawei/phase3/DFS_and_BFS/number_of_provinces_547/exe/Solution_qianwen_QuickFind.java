package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.number_of_provinces_547.exe;

public class Solution_qianwen_QuickFind {
    // 主函数
    public int findCircleNum(int[][] isConnected) {
        int cityAmount = isConnected.length;
        // 初始时，uf对象 是 一堆独立的节点
        UnionFind uf = new UnionFind(cityAmount);

        for (int currentCity = 0; currentCity < cityAmount; currentCity++) {
            for (int anotherCity = currentCity + 1; anotherCity < cityAmount; anotherCity++) { // 只需遍历上三角（对称矩阵）
                // 如果两个节点相连通，则：
                if (isConnected[currentCity][anotherCity] == 1) {
                    // 对它们进行合并
                    uf.union(currentCity, anotherCity);
                }
            }
        }

        // 返回 经过合并后 得到的连通分量的数量
        return uf.getComponentAmount();
    }
}

class UnionFind {
    // 数组的映射关系：当前节点 -> 图中它的父节点
    private int[] currentNodeToItsParent;
    // 图中 树（连通分量）的数量
    private int treeAmount;

    // 接受参数：节点数量
    public UnionFind(int nodeAmount) {
        currentNodeToItsParent = new int[nodeAmount];
        treeAmount = nodeAmount;

        // 元素初始化：初始化为元素本身
        for (int currentNode = 0; currentNode < nodeAmount; currentNode++) {
            currentNodeToItsParent[currentNode] = currentNode;
        }
    }

    // 找到 节点所属的连通分量的根节点
    public int findRootNodeOf(int currentNode) {
        // 如果 当前节点的父节点 不是 它本身，说明 当前节点 不是 根节点，则：
        if (currentNodeToItsParent[currentNode] != currentNode) {
            // 递归地 找到其父节点的根节点，并 作为当前节点的 父节点（路径压缩）
            currentNodeToItsParent[currentNode] = findRootNodeOf(currentNodeToItsParent[currentNode]); // 路径压缩
        }

        // 否则，返回 当前节点的父节点（也就是 根节点）
        return currentNodeToItsParent[currentNode];
    }

    // 合并两个节点所属的连通分量
    public void union(int node1, int node2) {
        int rootX = findRootNodeOf(node1);
        int rootY = findRootNodeOf(node2);

        if (rootX != rootY) {
            // 把 node1所在的树 与 node2所在的树 进行合并
            // 手段：把 rootX的父节点 设置为 rootY
            currentNodeToItsParent[rootX] = rootY;
            treeAmount--; // 合并后集合数减 1
        }
    }

    public int getComponentAmount() {
        return treeAmount;
    }
}