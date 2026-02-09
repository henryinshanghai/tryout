package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.binaryTree_levelOrderTraversal_102.exe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_qianwen_BFS {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> nodesInCurrentLevelList = new ArrayList<>();

        // 空树特判
        if (root == null) {
            return nodesInCurrentLevelList;
        }

        // 准备一个简单队列     用于维护‘当前层节点的顺序集合’
        Queue<TreeNode> nodeSimpleQueue = new LinkedList<>();
        nodeSimpleQueue.offer(root);

        while (!nodeSimpleQueue.isEmpty()) {
            // ⭐ 获取到 当前层的节点数量
            int nodeAmountInCurrentLevel = nodeSimpleQueue.size();
            List<Integer> currentLevelNodes = new ArrayList<>();

            // 处理 当前层的所有节点
            for (int currentNodeCursor = 0; currentNodeCursor < nodeAmountInCurrentLevel; currentNodeCursor++) {
                // 出队 当前层的当前节点
                TreeNode currentNode = nodeSimpleQueue.poll();
                // 把 该节点 添加到 当前层节点列表中
                currentLevelNodes.add(currentNode.val);

                /* 将 该节点的非空子节点 按序（先左后右）加入 队列（为下一层准备）*/
                if (currentNode.left != null) {
                    nodeSimpleQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    nodeSimpleQueue.offer(currentNode.right);
                }
            }

            // 把 当前层的节点列表 添加到 结果列表中
            nodesInCurrentLevelList.add(currentLevelNodes);
        }

        return nodesInCurrentLevelList;
    }
}
