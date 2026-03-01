package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.rotting_oranges_994.exe;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_qianwen_BFS_multipleStarts {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int orangesRotting(int[][] gridToItsOrangeState) {
        if (gridToItsOrangeState == null ||
                gridToItsOrangeState.length == 0) return 0;

        int rowAmount = gridToItsOrangeState.length;
        int colAmount = gridToItsOrangeState[0].length;
        Queue<int[]> rottenOrangeSpotSimpleQueue = new LinkedList<>();
        int freshOrangeAmount = 0;

        // 🧾 初始化：找 所有腐烂橘子（入队） 和 新鲜橘子（计数）
        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentColumn = 0; currentColumn < colAmount; currentColumn++) {
                // 如果是 腐烂的橘子
                if (gridToItsOrangeState[currentRow][currentColumn] == 2) {
                    // 则 入队
                    rottenOrangeSpotSimpleQueue.offer(new int[]{currentRow, currentColumn});
                } else if (gridToItsOrangeState[currentRow][currentColumn] == 1) { // 如果是 新鲜的橘子
                    // 则 计数
                    freshOrangeAmount++;
                }
            }
        }

        // 🚫 特判：没有新鲜橘子
        if (freshOrangeAmount == 0) {
            return 0;
        }

        int neededMinMinutes = 0;

        // 🔄 BFS 层序遍历
        while (!rottenOrangeSpotSimpleQueue.isEmpty()) {
            // 得到 当前分钟所存在的 腐烂橘子的数量
            int rottenOrangeAmountOnCurrLevel = rottenOrangeSpotSimpleQueue.size();

            /* 使用当前分钟 来 污染新鲜的橘子 */
            for (int currentRottenOrangeCursor = 0; currentRottenOrangeCursor < rottenOrangeAmountOnCurrLevel; currentRottenOrangeCursor++) {
                // 获取到 当前腐烂橘子的位置坐标
                int[] currentRottenOrangeSpot = rottenOrangeSpotSimpleQueue.poll();
                int currentRottenOrangeSpotX = currentRottenOrangeSpot[0],
                        currentRottenOrangeSpotY = currentRottenOrangeSpot[1];

                // 对于所有 有效的邻居方格（新鲜橘子）...
                for (int[] currentDirection : DIRS) {
                    // 当前方向上的下一个位置的坐标
                    int nextSpotXInCurrentDirection = currentRottenOrangeSpotX + currentDirection[0];
                    int nextSpotYInCurrentDirection = currentRottenOrangeSpotY + currentDirection[1];

                    // 如果 当前邻居 越界 or 非新鲜橘子（不需要污染）
                    if (nextSpotXInCurrentDirection < 0 ||
                            nextSpotXInCurrentDirection >= rowAmount ||
                            nextSpotYInCurrentDirection < 0 ||
                            nextSpotYInCurrentDirection >= colAmount || // 越界检查
                            gridToItsOrangeState[nextSpotXInCurrentDirection][nextSpotYInCurrentDirection] != 1) { // 业务检查：是否是新鲜橘子
                        // 则：→ 跳过
                        continue;
                    }

                    // 🍊 如果是 新鲜的橘子，则：
                    // ① 污染/腐烂 它
                    gridToItsOrangeState[nextSpotXInCurrentDirection][nextSpotYInCurrentDirection] = 2;
                    // ② 把 新鲜橘子的数量 - 1
                    freshOrangeAmount--;
                    // ③ 把 这个邻居位置 添加到队列中（以便能够 以之为基础，污染新的新鲜橘子）
                    rottenOrangeSpotSimpleQueue.offer(new int[]{nextSpotXInCurrentDirection, nextSpotYInCurrentDirection});
                }
            }

            // 只有在 本轮‘的确污染了新鲜橘子’的情况下，才把耗时+1
            // 原理：被污染的橘子 会被 添加到队列中
            if (!rottenOrangeSpotSimpleQueue.isEmpty()) {
                neededMinMinutes++; // 开始新的一分钟
            }
        }

        // ✅ 判断 是否 所有新鲜的橘子 都已全部腐烂
        return freshOrangeAmount == 0 ? neededMinMinutes : -1;
    }
}
