package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.binaryTree_inorder_traversal_94.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_recursion {
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return; // 终止条件

        dfs(node.left);       // 1. 左
        res.add(node.val);    // 2. 根
        dfs(node.right);      // 3. 右
    }
}
