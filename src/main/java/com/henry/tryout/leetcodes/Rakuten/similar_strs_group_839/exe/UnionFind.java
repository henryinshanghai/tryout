package com.henry.tryout.leetcodes.Rakuten.similar_strs_group_839.exe;

public class UnionFind {
    // 当前节点 -> 该节点的父节点 的映射  用于找到节点所在树的根节点
    private int[] currentNodeToItsParentNode;
    // 森林中 树的数量
    private int treeAmount;

    /**
     * 初始化 UF对象
     * ① 初始化成员变量；
     * ② 实现 具体的任务；
     *
     * @param nodeAmount 离散节点的数量
     */
    public UnionFind(int nodeAmount) {
        // 数组的容量初始化
        currentNodeToItsParentNode = new int[nodeAmount];

        // 森林中 树的数量 的初始化   - 最开始时，每个节点 都是一棵 独立的树
        treeAmount = nodeAmount;

        // 数组元素的初始化 - 初始时，每个节点的父节点 都是 它自己
        for (int currentNode = 0; currentNode < nodeAmount; currentNode++) {
            currentNodeToItsParentNode[currentNode] = currentNode;
        }
    }

    /**
     * 判断 两个节点之间 是否相连通
     *
     * @param nodeI 节点1
     * @param nodeJ 节点2
     * @return 连通则返回true；不连通则返回false
     */
    public boolean isBelongToSameComponent(int nodeI, int nodeJ) {
        // 获取到 节点1所属树的根节点
        int componentIdOfNodeI = findComponentIdOf(nodeI);
        // 获取到 节点2所属树的根节点
        int componentIdOfNodeJ = findComponentIdOf(nodeJ);

        // 查看 两个节点的根节点 是否相同
        return componentIdOfNodeI == componentIdOfNodeJ;
    }

    /**
     * 找到 指定节点 所属分组的 组ID
     *
     * @param currentNode 指定的节点
     * @return
     */
    public int findComponentIdOf(int currentNode) {
        // 如果 当前节点的父节点 不是 它自己，说明 它还不是根节点，则：
        if (currentNodeToItsParentNode[currentNode] != currentNode) {
            // 递归地 查找其父节点的根节点，并 进行路径压缩（把 查找到的根节点 作为 当前节点的父节点）
            currentNodeToItsParentNode[currentNode] = findComponentIdOf(currentNodeToItsParentNode[currentNode]); // 路径压缩
        }

        // 路径压缩后，树 变成了 一个只有两层的树 - 所有节点的父节点 都是 根节点
        return currentNodeToItsParentNode[currentNode];
    }

    /**
     * 把 两个指定的节点 连接起来
     *
     * @param node1
     * @param node2
     */
    public void unionToSameComponent(int node1, int node2) {
        int rootOfNode1 = findComponentIdOf(node1);
        int rootOfNode2 = findComponentIdOf(node2);

        // 如果 两个节点的根节点 不相同，说明 它们 现在还不在同一棵树中，
        if (rootOfNode1 != rootOfNode2) {
            // 则：把 这两棵树 合并成一棵树
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
