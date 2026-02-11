package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.number_of_provinces_547.exe;

// 无向图（使用二维矩阵表示）中的连通分量的数量
// 城市（节点）、省份（连通分量）
public class Solution_qianwen_DFS {

    public int findCircleNum(int[][] isConnected) {
        int cityAmount = isConnected.length;
        // 标记城市 是否已被访问
        boolean[] currentCityToHasVisited = new boolean[cityAmount];
        int provinceAmount = 0;

        for (int currentCity = 0; currentCity < cityAmount; currentCity++) {
            // 如果 当前城市 还没有被访问，说明 可以 以之为起点 进行DFS
            if (!currentCityToHasVisited[currentCity]) {
                // 则：① 新省份数量+1
                provinceAmount++;
                // ② 递归地搜索 该省份内的所有城市
                dfs(isConnected, currentCityToHasVisited, currentCity);
            }
        }

        return provinceAmount;
    }

    // 搜索并标记 当前节点所在连通分量中的所有节点
    private void dfs(int[][] graph,
                     boolean[] currentCityToHasVisited,
                     int currentCity) {
        // ① 标记 当前节点 ‘已访问’
        currentCityToHasVisited[currentCity] = true;

        // ② 遍历 所有城市
        for (int currentAnotherCity = 0; currentAnotherCity < graph.length; currentAnotherCity++) {
            if (graph[currentCity][currentAnotherCity] == 1 // 如果 该城市 与 当前城市 相连通
                    && !currentCityToHasVisited[currentAnotherCity]) { // 并且 该城市还没有被访问，
                // 则：递归地 访问该邻居
                dfs(graph, currentCityToHasVisited, currentAnotherCity);
            }
        }
    }
}
