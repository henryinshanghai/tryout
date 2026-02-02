package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.symmetric_tree_101.exe;

public class Solution_qianwen_recursion {
    public boolean isSymmetric(TreeNode root) {
        // 把 原始问题（二叉树是否轴对称） 扩展/泛化成 一个可以递归解决的问题（两棵树之间 是否互为镜像）
        if (root == null)
            return true;

        // 左子树 与 右子树 是否互为镜像
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode rootNodeOfTree1, TreeNode rootNodeOfTree2) {
        /* 递归终结条件 */
        if (rootNodeOfTree1 == null &&
                rootNodeOfTree2 == null) {
            return true;
        }

        if (rootNodeOfTree1 == null ||
                rootNodeOfTree2 == null) {
            return false;
        }

        // 当前层递归要做的事情：
        // ① 比较 左子节点和右子节点 是否相等（镜像定义）
        // ② 使用 子问题的解 来 帮助解决 原始问题
        return (rootNodeOfTree1.val == rootNodeOfTree2.val) &&
                isMirror(rootNodeOfTree1.left, rootNodeOfTree2.right) && // 外侧镜像
                isMirror(rootNodeOfTree1.right, rootNodeOfTree2.left); // 内侧镜像
    }
}
