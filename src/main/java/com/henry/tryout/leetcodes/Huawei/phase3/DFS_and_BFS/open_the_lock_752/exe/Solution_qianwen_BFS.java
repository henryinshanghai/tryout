package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.open_the_lock_752.exe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 不断转动每一个转轮 来 尝试密码的过程，对应于一幅 每个节点有 8 个邻居的图
// “一层” = 所有需要 相同旋转次数 才能到达的 状态集合。
// 最小旋转次数 = 目标状态所在的 BFS 层数。
public class Solution_qianwen_BFS {
    public int openLock(String[] deadCombis, String targetCombination) {
        Set<String> deadCombiSet = new HashSet<>(Arrays.asList(deadCombis));
        if (deadCombiSet.contains("0000")) {
            return -1;
        }

        Queue<String> combiSimpleQueue = new LinkedList<>();
        Set<String> triedCombiSet = new HashSet<>();

        combiSimpleQueue.offer("0000");
        triedCombiSet.add("0000");

        int stepAmount = 0;

        while (!combiSimpleQueue.isEmpty()) {
            // 获取到 当前层的 状态/节点数量
            // 🐖 这是N叉树BFS的特征
            int combiVariantAmountOnCurrentLevel = combiSimpleQueue.size();

            // 遍历 当前层 的所有状态/节点
            for (int currentCombiCursor = 0; currentCombiCursor < combiVariantAmountOnCurrentLevel; currentCombiCursor++) {
                // 获取当前层的当前节点/密码
                String currentCombination = combiSimpleQueue.poll();

                // 如果 该密码 与 目标密码 相同，说明 尝试到了正确密码，则：
                if (currentCombination.equals(targetCombination)) {
                    // 返回 尝试到正确密码所使用的步骤数量（BFS的算法 确保是）
                    return stepAmount;
                }

                // 对于每一个转轮（4个转轮），可能向前转一位，也可能向后转一位（2种选项）。因此统共8种可能/邻居状态
                for (int currentWheelCursor = 0; currentWheelCursor < 4; currentWheelCursor++) {
                    for (int currentRollOption : new int[]{-1, 1}) {
                        /* 计算当前尝试会产生的新密码 */
                        // 字符串 转 字符数组
                        char[] currentCombiCharSeq = currentCombination.toCharArray();

                        // 数字字符 -> 对应的int
                        int currentWheelDigit = currentCombiCharSeq[currentWheelCursor] - '0';
                        // 计算选择后(向前转、向后转)的新字符
                        int currentWheelNewDigit = (currentWheelDigit + currentRollOption + 10) % 10;
                        // 数字 -> 对应的字符
                        currentCombiCharSeq[currentWheelCursor] = (char) (currentWheelNewDigit + '0');
                        // 转回去 密码字符串
                        String nextCombiStr = new String(currentCombiCharSeq);

                        /* 处理 ‘尝试出的新密码’ */
                        if (!triedCombiSet.contains(nextCombiStr) && // 如果 该密码 还没有被尝试过
                                !deadCombiSet.contains(nextCombiStr)) { // 并且 该密码 不属于 死亡密码集合
                            // 说明 该密码 是一次‘有效尝试’，则：
                            // ① 把 它 添加到 ‘已尝试密码的集合’
                            triedCombiSet.add(nextCombiStr);
                            // ② 把 它 添加到 密码队列中（以便 能够以之为基础，尝试新的密码）
                            combiSimpleQueue.offer(nextCombiStr);
                        }
                    }
                }
            }

            // 本层处理完，步数 +1
            stepAmount++;
        }

        // BFS过程结束 但 仍旧没有找到 目标，说明 路径不存在，则 返回-1
        return -1;
    }
}
