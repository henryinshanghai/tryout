package com.henry.tryout.leetcodes.Rakuten.transform_BT_to_linkedlist_114.exe;

public class Solution_qianwen_Morris {
    public void flatten(TreeNode root) {
        TreeNode currentNode = root;

        while (currentNode != null) {
            /* 如果 当前节点 存在有 左子树，说明 当前树结构 违反了 链表结构所要求的 指针约束 和 顺序约束 */
            if (currentNode.leftSubNode != null) {
                // 则：把 左子树 整个搬运到 右分支中（右子树的上方）

                /* 找到 前驱节点（左子树的最右节点）*/
                // 指针 初始指向 当前节点的左子节点 处
                TreeNode predecessor = currentNode.leftSubNode;
                // 一路向右更新，直到 叶子节点处
                while (predecessor.rightSubNode != null) {
                    predecessor = predecessor.rightSubNode;
                }

                /* (找到前驱节点后) 按需搬移左子树 */
                // 手段：按需重建 关键节点 之间的关系
                // 前驱节点的右链接
                predecessor.rightSubNode = currentNode.rightSubNode;

                // 当前节点的左右链接
                currentNode.rightSubNode = currentNode.leftSubNode;
                currentNode.leftSubNode = null;
            }

            // Ⅱ （搬移完成左子树后）更新 游标指针currNode 指向到 下一个正确的位置上(当前节点的右子节点)
            currentNode = currentNode.rightSubNode;
        }
    }

}

class TreeNode {
    int value;

    TreeNode leftSubNode;
    TreeNode rightSubNode;
}