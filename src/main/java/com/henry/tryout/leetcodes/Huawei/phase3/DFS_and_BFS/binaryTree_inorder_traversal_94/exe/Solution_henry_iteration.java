package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.binaryTree_inorder_traversal_94.exe;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution_henry_iteration {
    Deque<TreeNode> nodeStack = new ArrayDeque<>();
    List<Integer> nodeValsInorder = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode currentRootNode) {

        while (currentRootNode != null ||
                !nodeStack.isEmpty()) {
            // 把 当前节点指针所指向的节点 到 最左侧叶子节点 路径上的所有节点 入栈
            // 因为 它们都是 ‘待处理的节点’
            while (currentRootNode != null) {
                nodeStack.push(currentRootNode);
                currentRootNode = currentRootNode.left; // 本轮的左
            }

            // 出栈叶子节点，并 移动 当前节点指针 指向它
            currentRootNode = nodeStack.pop(); // 本轮的根
            // 收集 节点
            nodeValsInorder.add(currentRootNode.val);

            // 把 当前节点指针 移动到 其右子节点处
            currentRootNode = currentRootNode.right; // 本轮的右（本轮 左-右-根 结束）
        }

        return nodeValsInorder;
    }
}
