package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.construct_binaryTree_105.exe;

import java.util.HashMap;
import java.util.Map;

// 流程：当前根节点val(前序序列) -> 该val在中序序列中的位置 -> 左子树-根节点-右子树
// 手段：
// 当前根节点val(前序序列)：currentRootNodeValCursor
// 该val在中序序列中的位置：nodeValToItsSpotInInorderSeqMap
// 使用中序序列来构建完整的树：buildTreeViaRangeInInorderSeq()
class Solution_qianwen_recursion {
    // 全局指针     用于指向当前根节点的val（前序序列：根-左-右，因此 当前节点总是某棵树的根）
    private int currentRootNodeValCursor = 0;

    public TreeNode buildTree(int[] preorderNodeSeq, int[] inorderNodeSeq) {
        Map<Integer, Integer> nodeValToItsSpotInInorderSeqMap = new HashMap<>();

        // 记录 节点值 -> 节点在’中序遍历结果序列‘中的位置（0-based）
        for (int currentNodeSpot = 0; currentNodeSpot < inorderNodeSeq.length; currentNodeSpot++) {
            int currentNodeOfInOrderSeq = inorderNodeSeq[currentNodeSpot];
            nodeValToItsSpotInInorderSeqMap.put(currentNodeOfInOrderSeq, currentNodeSpot);
        }

        return buildTreeViaRangeInInorderSeq(preorderNodeSeq,
                0,
                inorderNodeSeq.length - 1,
                nodeValToItsSpotInInorderSeqMap); // 🐖 这个Map 也可以提取为 全局变量 来 减少递归方法的参数
    }

    /**
     * 从 中序序列的指定区间中，构建出 一棵二叉树
     * @param preorderNodeSeq   前序序列    用于提供 当前根节点的val
     * @param currRangeLeftBar   指定区间的左边界（包含）
     * @param currRangeRightBar  指定区间的右边界（包含）
     * @param nodeValToItsSpotInInorderSeq   记录 节点值 -> 节点在中序序列中的位置 的map    用于提供当前根节点 在中序序列中的位置
     * @return  构建出的二叉树
     */
    private TreeNode buildTreeViaRangeInInorderSeq(int[] preorderNodeSeq,
                                                   int currRangeLeftBar,
                                                   int currRangeRightBar,
                                                   Map<Integer, Integer> nodeValToItsSpotInInorderSeq) {
        /* 〇 递归终结条件 */
        // 如果 区间的左边界 大于 区间的右边界，说明 构造过程结束，
        if (currRangeLeftBar > currRangeRightBar) {
            // 则：返回空树
            return null;
        }

        /* Ⅰ 当前级递归要做的事情 */
        // 获取到 当前根节点的val（来自 preorder[preIndex]）
        int currentRootNodeVal = preorderNodeSeq[currentRootNodeValCursor++];

        // ① 创建 根节点
        // 使用 ‘该根节点val’ 来 创建一个节点，作为 当前树的根节点
        TreeNode currentRootNode = new TreeNode(currentRootNodeVal);

        /* 使用子问题的解 来 帮助解决原始问题 */
        // 查找到 ’该根节点val‘ 在中序序列(左-根-右)中的位置
        // 🐖 要求 二叉树中 不能存在有 ‘重复的节点值’
        int rootValSpotInInorderSeq = nodeValToItsSpotInInorderSeq.get(currentRootNodeVal);

        // ②（使用中序序列的对应区间）递归地 构建左子树
        // 并把 构建出的树 绑定为 根节点的左子节点
        currentRootNode.left = buildTreeViaRangeInInorderSeq(
                preorderNodeSeq,
                currRangeLeftBar, // 左边界为 初始参数
                rootValSpotInInorderSeq - 1,
                nodeValToItsSpotInInorderSeq);

        // ③（使用中序序列的对应区间）递归地 构建右子树
        // 并把 构建出的树 绑定为 根节点的右子节点
        currentRootNode.right = buildTreeViaRangeInInorderSeq(preorderNodeSeq,
                rootValSpotInInorderSeq + 1,
                currRangeRightBar, // 右边界为 初始参数
                nodeValToItsSpotInInorderSeq);

        /* 返回原始问题的解 */
        // 最终返回 所构造出的树的根节点
        return currentRootNode;
    }
}
