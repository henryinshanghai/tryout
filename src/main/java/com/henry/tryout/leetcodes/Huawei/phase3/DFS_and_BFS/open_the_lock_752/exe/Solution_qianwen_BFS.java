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
        // 数组 转 集合  用于 方便地判断 指定元素是否存在
        Set<String> deadCombiSet = new HashSet<>(Arrays.asList(deadCombis));

        /* 处理初始密码"0000" */
        // 如果 初始密码/目标密码 就是’死亡密码‘，说明 无法解锁，
        if (deadCombiSet.contains(targetCombination) ||
                deadCombiSet.contains("0000")) {
            // 则：返回-1
            return -1;
        }

        // 准备一个队列   用于存储 当前正在尝试的 ’有效密码组合‘
        Queue<String> combiSimpleQueue = new LinkedList<>();
        // 准备一个集合   用于存储 已经尝试过的’有效密码组合‘
        Set<String> triedCombiSet = new HashSet<>();

        // 把 初始密码 添加到 ’有效密码‘队列中
        combiSimpleQueue.offer("0000");
        // 把 初始密码 添加到 ’已尝试密码‘集合中
        triedCombiSet.add("0000");

        int stepAmount = 0;

        /* 以当前密码为基础，不断旋转 来 尝试新的密码    手段：分层BFS */
        while (!combiSimpleQueue.isEmpty()) {
            // 获取到 当前层的 所有’密码组合‘的数量
            // 🐖 这是 分层BFS 的特征
            int combiVariantAmountOnCurrentLevel = combiSimpleQueue.size();

            // 遍历 当前层 所有的密码组合
            for (int currentCombiCursor = 0; currentCombiCursor < combiVariantAmountOnCurrentLevel; currentCombiCursor++) {
                // 获取 当前层的 当前节点/密码
                String currentCombination = combiSimpleQueue.poll();

                // 如果 该密码 与 目标密码 相同，说明 尝试到了 正确密码，
                if (currentCombination.equals(targetCombination)) {
                    // 则：返回 尝试到正确密码 所使用的步骤数量（BFS算法 确保是 最少步骤）
                    return stepAmount;
                }

                /* 以 当前密码组合 为基础，开始尝试 新的密码组合（共8种） */
                // 对于每一个转轮（4个转轮），都有2种选项：可能向前转一位，也可能向后转一位。因此统共有 8种可能/邻居状态
                for (int currentWheelCursor = 0; currentWheelCursor < 4; currentWheelCursor++) {
                    // 两种旋转选项   手段：一个数字数组 {-1, 1}
                    for (int currentRollOption : new int[]{-1, 1}) {
                        /* 计算 当前尝试 会产生的新密码 */
                        // 把 字符串 转成 字符数组
                        // 🐖 这个char[]写在内层循环中 来 保证每次尝试新密码时，都是基于 相同的’当前密码组合‘
                        char[] currentCombiCharSeq = currentCombination.toCharArray();

                        // 数字字符 -> 对应的int数字     手段：该数字字符 - '0'
                        int originalDigitOnCurrWheel = currentCombiCharSeq[currentWheelCursor] - '0';
                        // 计算 选择后(向前转、向后转)的新字符
                        // 🐖 9往后转一位 会回到0，而不是10.     手段：把 加和结果 先+10，再%10
                        int newDigitOnCurrWheel = (originalDigitOnCurrWheel + currentRollOption + 10) % 10;
                        // 数字 -> 对应的字符      手段：先 把该数字+'0'，再把 加和结果(int数据) 强转为 char
                        currentCombiCharSeq[currentWheelCursor] = (char) (newDigitOnCurrWheel + '0');
                        // 密码字符数组 转回去 密码字符串，得到 ’当前尝试出的新密码组合‘
                        String currTriedCombiStr = new String(currentCombiCharSeq);

                        /* 处理 ‘尝试出的新密码组合’ */
                        if (!triedCombiSet.contains(currTriedCombiStr) && // 如果 该密码 还没有被尝试过
                                !deadCombiSet.contains(currTriedCombiStr)) { // 并且 该密码 不属于 死亡密码集合
                            // 说明 该密码 是一次‘有效尝试’，则：
                            // ① 把 它 添加到 ’有效密码队列‘中（以便 能够以之为基础，尝试新的密码）
                            combiSimpleQueue.offer(currTriedCombiStr);
                            // ② 把 它 添加到 ‘已尝试密码的集合’
                            triedCombiSet.add(currTriedCombiStr);
                        }
                    }
                }
            }

            // 本层处理完，步数 +1
            // 原理：每次旋转，在BFS树中都会产生新的一层密码组合
            stepAmount++;
        }

        // BFS过程结束 但 仍旧没有找到 目标，
        // 说明 路径不存在，则 返回-1
        return -1;
    }
}
