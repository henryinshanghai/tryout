package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.shortestPath_in_binaryMatrix_1091.exe;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_qianwen_BFS {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int rowAmount = grid.length;

        // 特判：起点 或 终点 是障碍
        if (grid[0][0] == 1 ||
                grid[rowAmount - 1][rowAmount - 1] == 1) {
            return -1;
        }

        // 特判：1x1 网格
        if (rowAmount == 1) return 1;

        // 定义8 个方向的偏移量
        int[][] ALL_DIRS = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        // 队列元素：存储 {行坐标, 列坐标, 当前路径长度}
        // 🐖 在状态中 包含了距离，相当于 隐式分层
        // 把 “距离” 作为 状态的一部分 存储在队列中，而不是 用 全局变量steps 表示当前层
        Queue<int[]> statusSimpleQueue = new LinkedList<>();
        statusSimpleQueue.offer(new int[]{0, 0, 1});

        // 访问标记（也可直接修改 grid，但题目 可能要求 不修改原数组）
        boolean[][] currentSpotToHasVisited = new boolean[rowAmount][rowAmount];
        currentSpotToHasVisited[0][0] = true;

        while (!statusSimpleQueue.isEmpty()) {
            // 获取到 队列中的当前状态
            int[] currentStatus = statusSimpleQueue.poll();
            // 状态中的方格位置、路径长度
            int currRow = currentStatus[0];
            int currCol = currentStatus[1];
            int itsPathLength = currentStatus[2];

            // 尝试 8 个方向上的邻居方格...
            for (int[] currentDirection : ALL_DIRS) {
                int neighborSpotX = currRow + currentDirection[0];
                int neighborSpotY = currCol + currentDirection[1];

                // 检查 新位置 是否合法
                if (neighborSpotX >= 0 && neighborSpotX < rowAmount &&
                        neighborSpotY >= 0 && neighborSpotY < rowAmount && // 越界检查
                        grid[neighborSpotX][neighborSpotY] == 0 && // 业务约束检查
                        !currentSpotToHasVisited[neighborSpotX][neighborSpotY]) { // 重复访问检查

                    // 如果 当前邻居方格 是 终点方格，说明 找到了 到达终点的路径，
                    if (neighborSpotX == rowAmount - 1 &&
                            neighborSpotY == rowAmount - 1) {
                        // 则：路径长度 = 当前方格的路径 + 1（终点方格的计数）
                        return itsPathLength + 1;
                    }

                    // 标记该邻居方格 并 入队它
                    currentSpotToHasVisited[neighborSpotX][neighborSpotY] = true;
                    statusSimpleQueue.offer(new int[]{neighborSpotX, neighborSpotY, itsPathLength + 1});
                }
            }
        }

        return -1; // 无法到达
    }
}
