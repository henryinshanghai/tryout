package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.validate_BST_98.exe;

import java.util.Stack;

// Carl提供的写法
public class Solution_qianwen_iteration2 {
    public boolean isValidBST(TreeNode root) {
        /* 原理：BST的中序遍历 节点结果序列 应该是一个 严格递增的序列 */
        // 手段：在 对树进行中序遍历的过程中，判断 该树是否满足 BST的约束

        /* 为验证BST而新增的代码段👇 */
        // 与iteration1的区别①：这里使用的是 TreeNode局部变量，而不是integer。
        TreeNode previousNode = null; // Integer.MIN_VALUE;
        /* 为验证BST而新增的代码段👆 */

        // 准备一个栈，用于 处理节点
        Stack<TreeNode> nodeStack = new Stack<>();
        // 准备 节点指针，用于 访问节点
        TreeNode currentNode = root;

        while (currentNode != null || !nodeStack.isEmpty()) {
            // 与 iteration1的区别②：这里使用了 if/else的写法
            if (currentNode != null) {
                nodeStack.push(currentNode); // 添加 ’待处理的节点‘ 到栈中
                currentNode = currentNode.left; // 左
            } else {
                currentNode = nodeStack.pop(); // 根

                /* 这一段相对于模板代码 是新增的，
                用于 判断当前处理的节点序列 是否违反了 BST的约束 */
                if (previousNode != null && currentNode.val <= previousNode.val) {
                    return false;
                }
                // 记录 ’当前处理的节点‘
                previousNode = currentNode;

                // 移动’节点指针‘ 到 右子节点
                currentNode = currentNode.right; // 右
            }
        }

        return true;
    }
}
