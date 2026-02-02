package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.validate_BST_98.exe;

import java.util.Stack;

// 对于二叉树节点的遍历，区分 ’访问‘节点的时机 VS. ’处理‘节点的时机
public class Solution_qianwen_iteration1 {
    public boolean isValidBST(TreeNode currentRootNode) {
        Stack<TreeNode> stack = new Stack<>();
        long previousNodeVal = Long.MIN_VALUE;

        /* 原理：BST的中序遍历序列 必然是 严格单调递增的 */
        while (currentRootNode != null || !stack.isEmpty()) {
            /* ① 当 当前节点不为nil节点 时 */
            // 一路向左，把 搜索路径上 访问到的所有节点 都 添加到栈中
            while (currentRootNode != null) {
                // ’访问到的‘当前节点
                stack.push(currentRootNode);
                currentRootNode = currentRootNode.left;
            } /* 循环结束时，栈顶元素 是 最左侧的叶子节点 */

            /* ② 如果 当前节点为nil节点（while循环的退出条件），说明 当前路径搜索结束 */
            // 弹出 当前的栈顶元素（叶子节点），开始’处理‘它
            currentRootNode = stack.pop();

            // 如果 当前节点的val <= ’上一个访问的节点‘的val，
            // 说明 当前的序列 违反了 BST的约束（中序遍历的结果序列 应为 升序），
            if (currentRootNode.val <= previousNodeVal) {
                // 则：该 二叉树 不是 BST；
                return false;
            }

            // 把 ’当前被处理的节点‘ 记录为 ’序列中的上一个节点‘
            previousNodeVal = currentRootNode.val;

            // 把 当前节点指针 移动到 其右子节点处（因为已经 处理了 左子节点 与 当前节点了）
            currentRootNode = currentRootNode.right;
        }

        return true;
    }
}
