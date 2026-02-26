package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.validate_BST_98.exe;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// 对于二叉树节点的遍历，区分 ’访问‘节点的时机 VS. ’处理‘节点的时机
public class Solution_qianwen_iteration1 {
    public boolean isValidBST(TreeNode currentRootNode) {
        /* 为校验BST而新增的代码👇 */
        // 准备一个‘前一个节点的值’的变量
        // 用于存储 遍历过程中的‘当前节点的值’ 来 参与后继比较
        long previousNodeVal = Long.MIN_VALUE;
        /* 为校验BST而新增的代码👆 */

        // 准备一个栈 用于存储非nil节点
        Deque<TreeNode> nodeStack = new ArrayDeque<>();

        /* 原理：BST的中序遍历序列 必然是 严格单调递增的 */
        while (currentRootNode != null || !nodeStack.isEmpty()) {
            /* 把 从 当前节点指针所指向的节点 到 最左侧的叶子节点 路径上的 所有节点，都入栈 */
            // 如果 当前节点为nil节点，就会跳过while，直接执行pop
            while (currentRootNode != null) {
                // ’访问到的‘当前节点
                nodeStack.push(currentRootNode);
                // 更新 当前节点指针 指向 其左子节点
                currentRootNode = currentRootNode.left; // 本轮的左
            } /* 循环结束时，栈顶元素 是 最左侧的叶子节点 */

            /* 出栈 最左侧的叶子节点，并 移动 当前节点指针 指向它 */
            currentRootNode = nodeStack.pop(); // 本轮的根

            /* 为了校验BST而添加的代码（非前序遍历模板代码）👇 */
            // 如果 当前节点的val <= ’上一个访问的节点‘的val，
            // 说明 当前的序列 违反了 BST的约束（中序遍历的结果序列 应为 升序），
            if (currentRootNode.val <= previousNodeVal) {
                // 则：该 二叉树 不是 BST；
                return false;
            }

            // 把 ’当前被处理的节点‘ 记录为 ’序列中的上一个节点‘
            previousNodeVal = currentRootNode.val;
            /* 为了校验BST而添加的代码（非前序遍历模板代码）👆 */

            /* 把 当前节点指针 移动到 其右子节点处 */
            currentRootNode = currentRootNode.right; // 本轮的根（当前轮的 左-根-右 结束）
        }

        return true;
    }
}
