package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.validate_BST_98.exe;

// 把 二叉树 视为 左子树 + 根节点 + 右子树 的递归结构，
// 使用 递归的方式 来 进行 节点的中序遍历；
public class Solution_qianwen_recursion {
//    TreeNode previousNode = null;
    // 准备一个成员变量     用于记录‘序列中的上一个节点’
    // 🐖 成员变量 会在 方法的多次调用 之间 保持状态
    // 因此 如果需要对方法多次调用（比如需要在本地测试多组数据）的话，必须要 每次手动重置 prev
    // 为了处理这个问题👆，可以把prev 添加为 递归方法的参数（略）
    long previousNodeValInSeq = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode currentRootNode) {
        // 〇 递归终结条件：当前节点 是一个 nil节点
        // 〇 简单情形：nil节点 是一棵BST
        if (currentRootNode == null) {
            // 返回true 给上一级调用
            return true;
        }

        /* 左 */
        // 如果 左子树不是一个BST，
        if (!isValidBST(currentRootNode.left)) {
            // 则：返回false
            return false;
        }

        /* 根 */
        /* 比较 当前节点的val 与 序列中的上一个节点的val */
        // 如果 当前节点 <= 中序遍历的前一个节点，说明不满足BST，
        if (currentRootNode.val <= previousNodeValInSeq) { // previousNode.val;
            // 则：返回 false；
            return false;
        }
        // 把 当前节点的value 记录为 ’序列中的上一个节点的value‘
        previousNodeValInSeq = currentRootNode.val; // previousNode = currentNode

        /* 右 */
        return isValidBST(currentRootNode.right);
    }
}
