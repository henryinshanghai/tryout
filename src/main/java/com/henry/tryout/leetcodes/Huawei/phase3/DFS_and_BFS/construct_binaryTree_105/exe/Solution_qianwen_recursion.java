package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.construct_binaryTree_105.exe;

import java.util.HashMap;
import java.util.Map;

class Solution_qianwen_recursion {
    private int currentRootNodeCursor = 0; // 全局指针，指向当前要处理的 preorder 元素

    public TreeNode buildTree(int[] preorderNodeSeq, int[] inorderNodeSeq) {
        Map<Integer, Integer> nodeValToItsSpotOfInOrderSeq = new HashMap<>();

        // 记录 节点值 -> 节点在’中序遍历结果序列‘中的位置（0-based）
        for (int currentNodeSpot = 0; currentNodeSpot < inorderNodeSeq.length; currentNodeSpot++) {
            int currentNodeOfInOrderSeq = inorderNodeSeq[currentNodeSpot];
            nodeValToItsSpotOfInOrderSeq.put(currentNodeOfInOrderSeq, currentNodeSpot);
        }

        return buildTreeViaRangeInInorderSeq(preorderNodeSeq,
                0,
                inorderNodeSeq.length - 1,
                nodeValToItsSpotOfInOrderSeq);
    }

    /**
     * 从 中序序列的指定区间中，构建出 一棵二叉树
     * @param preorderNodeSeq   前序序列    用于提供树的根节点的val
     * @param leftBar   指定区间的左边界（包含）
     * @param rightBar  指定区间的右边界（包含）
     * @param nodeValToItsSpotInInorderSeq   记录 节点值 -> 节点在中序序列中的位置 的map    用于提供子树的边界
     * @return  构建出的二叉树
     */
    private TreeNode buildTreeViaRangeInInorderSeq(int[] preorderNodeSeq,
                                                   int leftBar,
                                                   int rightBar,
                                                   Map<Integer, Integer> nodeValToItsSpotInInorderSeq) {
        /* 〇 递归终结条件 */
        // 如果 区间的左边界 大于 区间的右边界，说明 构造过程结束，
        if (leftBar > rightBar) {
            // 则：返回空树
            return null;
        }

        /* Ⅰ 当前级递归要做的事情 */
        // 创建 根节点
        // ① 从前序序列（根-左-右）中，获取到 当前树的根节点val（来自 preorder[preIndex]）
        int currentRootNodeVal = preorderNodeSeq[currentRootNodeCursor++];
        // ② 使用获取到的val 来 创建一个节点，作为 当前树的根节点
        TreeNode currentRootNode = new TreeNode(currentRootNodeVal);

        /* 使用子问题的解 来 帮助解决原始问题 */
        // 查找到 该根节点 在’中序遍历结果序列(左-根-右)‘中的位置
        // 🐖 要求 二叉树中 不能存在有 ‘重复的节点值’
        int currentRootNodeSpot = nodeValToItsSpotInInorderSeq.get(currentRootNodeVal);

        // （使用’中序遍历结果序列‘中 当前根节点 左边的区间范围）递归地 构建左子树
        // 并把 构建出的树 绑定为 根节点的左子节点
        currentRootNode.left = buildTreeViaRangeInInorderSeq(
                preorderNodeSeq,
                leftBar,
                currentRootNodeSpot - 1,
                nodeValToItsSpotInInorderSeq);

        // （使用’中序遍历结果序列‘中 当前根节点 右边的区间范围）递归地 构建右子树
        // 并把 构建出的树 绑定为 根节点的右子节点
        currentRootNode.right = buildTreeViaRangeInInorderSeq(preorderNodeSeq,
                currentRootNodeSpot + 1,
                rightBar,
                nodeValToItsSpotInInorderSeq);

        /* 返回原始问题的解 */
        // 最终返回 所构造出的树的根节点
        return currentRootNode;
    }
}
