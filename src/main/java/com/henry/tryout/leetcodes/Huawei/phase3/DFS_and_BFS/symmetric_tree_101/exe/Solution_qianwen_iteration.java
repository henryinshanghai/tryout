package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.symmetric_tree_101.exe;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_qianwen_iteration {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 准备一个简单队列     手段：使用LinkedList（双向链表，头尾操作都是O(1)）
        Queue<TreeNode> nodeContainer = new LinkedList<>();
        // 按序依次入队 左子节点、右子节点
        nodeContainer.offer(root.left);
        nodeContainer.offer(root.right);

        while (!nodeContainer.isEmpty()) {
            // 从容器中 取出一对节点（预期这对节点 会是 镜像节点）
            TreeNode leftOfCurrPair = nodeContainer.poll();
            TreeNode rightOfCurrPair = nodeContainer.poll();

            /* 检查 这两个节点 是否相同（互为镜像） */
            // 两者都空，说明 当前节点对 互为镜像，
            if (leftOfCurrPair == null &&
                    rightOfCurrPair == null) {
                // 则：跳过对 ‘当前节点对’ 的后继检查，直接检查 下一个节点对
                continue;
            }

            // 如果 不对称（一个空或值不等），说明 当前节点对 不互为镜像，
            if (leftOfCurrPair == null ||
                    rightOfCurrPair == null ||
                    leftOfCurrPair.val != rightOfCurrPair.val) {
                // 则：返回false 表示 两棵树 不是 互为镜像的
                return false;
            }

            /* 把 当前节点对 的左右子节点，以 节点对 添加到 队列中 */
            // ‘外侧’节点对：左子节点的左子节点、右子节点的右子节点
            nodeContainer.offer(leftOfCurrPair.left);
            nodeContainer.offer(rightOfCurrPair.right);

            // ‘内测’节点对：左子节点的右子节点、右子节点的左子节点
            nodeContainer.offer(leftOfCurrPair.right);
            nodeContainer.offer(rightOfCurrPair.left);
        }

        return true;
    }
}
