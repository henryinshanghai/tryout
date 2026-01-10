package com.henry.tryout.leetcodes.Rakuten.transform_BT_to_linkedlist_114.exe;

public class Solution_qianwen {
    public void flatten(TreeNode root) {
        TreeNode currentNode = root;

        while (currentNode != null) {
            /* Ⅰ 如果存在有左子树，说明 当前树结构 违反了 链表结构所要求的 指针约束 和 顺序约束，
                则：按需调整 树结构（把左分支的曲线拉直） */
            // 把 左子树与右子树 按照前序序列的顺序 拼接起来，挂到 当前节点右边
            if (currentNode.leftSubNode != null) {
                // ① 找到 左子树的最右节点（前驱）
                TreeNode predecessor = currentNode.leftSubNode;
                // 从 predecessor 一路向右查找 符合条件的节点(右子节点为null)
                while (predecessor.rightSubNode != null) {
                    predecessor = predecessor.rightSubNode;
                }

                // ② 将 当前节点的右子树 挂到 其前驱节点的右边
                predecessor.rightSubNode = currentNode.rightSubNode;

                // ③ 把 当前节点的左子树 移到右边
                currentNode.rightSubNode = currentNode.leftSubNode;
                currentNode.leftSubNode = null;
            }

            // 使用 当前节点 来 推进到 ”下一个前序节点“(当前节点的右子节点)
            currentNode = currentNode.rightSubNode;
        }
    }

}

class TreeNode {
    int value;

    TreeNode leftSubNode;
    TreeNode rightSubNode;
}