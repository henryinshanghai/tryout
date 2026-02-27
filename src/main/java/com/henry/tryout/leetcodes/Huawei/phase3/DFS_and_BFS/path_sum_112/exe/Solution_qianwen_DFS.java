package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.path_sum_112.exe;

public class Solution_qianwen_DFS {
    public boolean hasPathSum(TreeNode currentNode, int currentTargetSum) {
        /* 情形① 搜索过程 到达了 nil节点 */
        // 如果 当前节点为 null，说明 搜索过程 到达了nil节点，则：
        if (currentNode == null) {
            // 返回 false，表示 不存在 满足条件的路径
            return false;
        }

        /* 情形② 搜索过程 到达了 叶子节点 */
        // 如果 当前节点的左右子节点 都是null，
        // 说明 搜索过程 到达了 叶子节点，则：
        if (currentNode.left == null &&
                currentNode.right == null) {
            // 判断 叶子节点的值 与 当前所期待的目标值 是否相等，并 把判断结果 返回给上一级调用
            return currentNode.val == currentTargetSum;
        }

        /* 情形③ 搜索过程 到达了中间节点 */
        // 递归地判断 左子树 或 右子树 中是否存在 满足条件的路径
        return hasPathSum(currentNode.left,
                currentTargetSum - currentNode.val) || // 检查 左子树中是否存在 对应的子路径
                hasPathSum(currentNode.right,
                        currentTargetSum - currentNode.val); // 检查 右子树中是否存在 对应的子路径
    }
}
