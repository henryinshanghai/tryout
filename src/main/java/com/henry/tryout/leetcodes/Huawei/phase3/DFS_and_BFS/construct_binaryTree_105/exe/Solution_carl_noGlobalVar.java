package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.construct_binaryTree_105.exe;

import java.util.HashMap;
import java.util.Map;

public class Solution_carl_noGlobalVar {
    Map<Integer, Integer> nodeValToItsSpotInInorderSeqMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        nodeValToItsSpotInInorderSeqMap = new HashMap<>();
        for (int currentNodeSpot = 0; currentNodeSpot < inorder.length; currentNodeSpot++) { // 用map保存中序序列的数值对应位置
            nodeValToItsSpotInInorderSeqMap.put(inorder[currentNodeSpot], currentNodeSpot);
        }

        return buildTreeViaNodesInRange(preorder, 0, preorder.length, 0, inorder.length);  // 前闭后开
    }

    // 🐖 写之前确认好区间的开闭性，并保持其一致。这里使用的是 左闭右开区间
    public TreeNode buildTreeViaNodesInRange(int[] preorder,
                                             int preBegin,
                                             int preEnd,
                                             int inBegin,
                                             int inEnd) {
        // 递归终止条件：（左闭右开）区间无效
        if (preBegin >= preEnd || inBegin >= inEnd) {  // 不满足左闭右开，说明没有元素，返回空树
            return null;
        }

        // 前序的第一个元素 是 当前子树的根
        int currentRootNodeVal = preorder[preBegin];
        TreeNode currentRootNode = new TreeNode(currentRootNodeVal);  // 构造结点

        // 在中序中 找到根的位置
        int currentRootNodeSpot = nodeValToItsSpotInInorderSeqMap.get(currentRootNodeVal);

        // 计算 左子树的节点数量
        int leftSubtreeSize = currentRootNodeSpot - inBegin;

        // 递归构建左子树：
        // - 前序：从 preStart+1 开始，共 leftSubtreeSize 个元素 → [preStart+1, preStart+leftSubtreeSize + 1)
        // - 中序：[inStart, rootIndexInInorder)
        currentRootNode.left = buildTreeViaNodesInRange(
                preorder, preBegin + 1, preBegin + leftSubtreeSize + 1,
                inBegin, currentRootNodeSpot);


        // 递归构建右子树：
        // - 前序：紧跟左子树之后 → [preStart + leftSubtreeSize + 1, preEnd)
        // - 中序：[rootIndexInInorder + 1, inEnd)
        currentRootNode.right = buildTreeViaNodesInRange(
                preorder, preBegin + leftSubtreeSize + 1, preEnd,
                currentRootNodeSpot + 1, inEnd);

        return currentRootNode;
    }
}
