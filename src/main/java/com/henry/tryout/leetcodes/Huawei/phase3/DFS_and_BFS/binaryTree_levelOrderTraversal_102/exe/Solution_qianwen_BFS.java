package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.binaryTree_levelOrderTraversal_102.exe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_qianwen_BFS {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> nodesInCurrentLevelList = new ArrayList<>();

        // ① 对特殊参数 进行单独处理
        if (root == null) {
            return nodesInCurrentLevelList;
        }

        // 准备一个简单队列     用于维护‘当前层节点的顺序集合’
        Queue<TreeNode> nodeSimpleQueue = new LinkedList<>();
        nodeSimpleQueue.offer(root);

        while (!nodeSimpleQueue.isEmpty()) {
            // ② size应该是个静态的值，需要写在for循环的外部（不能在循环过程中发生变化）
            int nodeAmountInCurrentLevel = nodeSimpleQueue.size();
            List<Integer> currentLevelNodes = new ArrayList<>();

            // 处理 当前层的所有节点
            for (int currentNodeCursor = 0; currentNodeCursor < nodeAmountInCurrentLevel; currentNodeCursor++) {
                // 出队 当前层的当前节点
                TreeNode currentNode = nodeSimpleQueue.poll();
                // 把 该节点 添加到 当前层节点列表中
                currentLevelNodes.add(currentNode.val);

                /* 将 该节点的非空子节点 按序（先左后右）加入 队列（为下一层准备）*/
                // ③ 只把非nil节点添加到队列中
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
