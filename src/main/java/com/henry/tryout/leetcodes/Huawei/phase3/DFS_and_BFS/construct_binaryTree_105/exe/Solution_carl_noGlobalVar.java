package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.construct_binaryTree_105.exe;

import java.util.HashMap;
import java.util.Map;

// 前序序列：根-左-右
// 中序序列：左-根-右
// 手段：先获取到 当前根节点val 在中序序列中的位置 rootValSpotInInorderSeq，再 由之计算 左子树、右子树所对应的区间；
// 参考代码的计算过程👇
public class Solution_carl_noGlobalVar {
    Map<Integer, Integer> nodeValToItsSpotInInorderSeqMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // val -> 该val在序列中出现的位置（要求 序列中 不存在重复元素）
        nodeValToItsSpotInInorderSeqMap = new HashMap<>();
        for (int currentNodeSpot = 0; currentNodeSpot < inorder.length; currentNodeSpot++) {
            nodeValToItsSpotInInorderSeqMap.put(inorder[currentNodeSpot], currentNodeSpot);
        }

        return buildTreeViaNodesInRange(preorder,
                0,
                preorder.length,
                0,
                inorder.length);  // 前闭后开
    }

    // 🐖 写之前 先确认好 区间的开闭性，并 整个过程保持其一致。
    // 这里 使用的是 左闭右开区间

    /**
     * 从 前序序列的指定区间 与 其所对应的中序序列的对应区间 中，构建出 二叉树
     *
     * @param preorder              前序序列
     * @param preorderRangeLeftBar  前序序列中 区间的左边界
     * @param preorderRangeRightBar 前序序列中 区间的右边界
     * @param inorderRangeLeftBar   中序序列中 区间的左边界
     * @param inorderRangeRightBar  中序序列中 区间的右边界
     * @return 构建出的二叉树
     */
    public TreeNode buildTreeViaNodesInRange(int[] preorder,
                                             int preorderRangeLeftBar,
                                             int preorderRangeRightBar,
                                             int inorderRangeLeftBar,
                                             int inorderRangeRightBar) {
        // 递归终止条件：（左闭右开）区间无效
        if (preorderRangeLeftBar >= preorderRangeRightBar ||
                inorderRangeLeftBar >= inorderRangeRightBar) {  // 不满足左闭右开，说明没有元素，返回空树
            return null;
        }

        // 前序的第一个元素 是 当前子树的根
        int currentRootNodeVal = preorder[preorderRangeLeftBar];
        // 使用 当前根节点val 来 创建节点
        TreeNode currentRootNode = new TreeNode(currentRootNodeVal);

        // 使用 当前根节点val 来 得到 其在中序序列中的位置
        int rootValSpotInInorderSeq = nodeValToItsSpotInInorderSeqMap.get(currentRootNodeVal);

        // 计算 左子树的节点数量
        int leftSubtreeNodeAmount = rootValSpotInInorderSeq - inorderRangeLeftBar;

        // 递归构建左子树：
        // - 所对应的前序区间：[preStart+1, preStart+leftSubtreeNodeAmount + 1)
        // - 所对应的中序区间：[inStart, rootIndexInInorder)
        currentRootNode.left = buildTreeViaNodesInRange(
                preorder,
                preorderRangeLeftBar + 1,
                preorderRangeLeftBar + leftSubtreeNodeAmount + 1,
                inorderRangeLeftBar,
                rootValSpotInInorderSeq);


        // 递归构建右子树：
        // - 所对应的前序区间：紧跟左子树之后 → [preStart + leftSubtreeNodeAmount + 1, preEnd)
        // - 所对应的中序区间：[rootIndexInInorder + 1, inEnd)
        currentRootNode.right = buildTreeViaNodesInRange(
                preorder,
                preorderRangeLeftBar + leftSubtreeNodeAmount + 1,
                preorderRangeRightBar,
                rootValSpotInInorderSeq + 1,
                inorderRangeRightBar);

        return currentRootNode;
    }
}
