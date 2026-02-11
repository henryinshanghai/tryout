package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.number_of_provinces_547.exe;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_qianwen_BFS {
    public int findCircleNum(int[][] isConnected) {
        // 城市数量
        int cityAmount = isConnected.length;
        // 标记城市是否被访问过
        boolean[] cityToHasVisited = new boolean[cityAmount];
        // 省份数量计数器
        int provinceAmount = 0;

        // 遍历每个城市
        for (int currentCity = 0; currentCity < cityAmount; currentCity++) {
            // 如果 当前城市 尚未被访问，说明 发现了 一个新的省份
            if (!cityToHasVisited[currentCity]) {
                // 省份数 +1
                provinceAmount++;
                // 从城市 currentCity 开始 BFS，标记整个连通分量
                bfs(isConnected, cityToHasVisited, currentCity);
            }
        }

        return provinceAmount;
    }

    /**
     * 从 startCity 出发，使用 BFS 遍历所有与其连通的城市，并标记为已访问
     *
     * @param graph            邻接矩阵（isConnected）
     * @param cityToHasVisited 访问标记数组
     * @param startCity        起始城市
     */
    private void bfs(int[][] graph, boolean[] cityToHasVisited, int startCity) {
        Queue<Integer> citySimpleQueue = new LinkedList<>();

        // 将 起始城市 加入队列，并 标记为 已访问
        citySimpleQueue.offer(startCity);
        cityToHasVisited[startCity] = true;

        // BFS 主循环：只要队列非空，就继续扩展
        while (!citySimpleQueue.isEmpty()) {
            // 出队 队首城市
            int currentCity = citySimpleQueue.poll();

            // 遍历所有的城市（0 到 n-1）
            for (int anotherCity = 0; anotherCity < graph.length; anotherCity++) {
                // 如果 currentCity 与 anotherCity 相连，且 anotherCity 未被访问
                if (graph[currentCity][anotherCity] == 1 &&
                        !cityToHasVisited[anotherCity]) {
                    cityToHasVisited[anotherCity] = true;       // 标记 其 为 已访问
                    citySimpleQueue.offer(anotherCity);          // 加入 队列，用于 后续扩展
                }
            }
        }
    }
}
