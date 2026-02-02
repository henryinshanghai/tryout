package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.shortestPath_with_alternatingColor_1129.exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_qianwen_BFS {
    private static final int RED = 0;
    private static final int BLUE = 1;

    public int[] shortestAlternatingPaths(int nodeAmount, int[][] redEdges, int[][] blueEdges) {
        // 构建邻接表：currentNodeToItsColoredNeighbors[color][u] = 所有从 u 出发的 color 色边的终点列表
        List<Integer>[][] currentNodeToItsColoredNeighbors = new List[2][nodeAmount];
        for (int currentNode = 0; currentNode < nodeAmount; currentNode++) {
            currentNodeToItsColoredNeighbors[RED][currentNode] = new ArrayList<>();
            currentNodeToItsColoredNeighbors[BLUE][currentNode] = new ArrayList<>();
        }

        for (int[] currentRedEdge : redEdges) {
            currentNodeToItsColoredNeighbors[RED][currentRedEdge[0]].add(currentRedEdge[1]);
        }
        for (int[] currentBlueEdge : blueEdges) {
            currentNodeToItsColoredNeighbors[BLUE][currentBlueEdge[0]].add(currentBlueEdge[1]);
        }

        // 初始化结果数组
        int[] nodeToShortestAlternativePathReachIt = new int[nodeAmount];
        Arrays.fill(nodeToShortestAlternativePathReachIt, -1);
        nodeToShortestAlternativePathReachIt[0] = 0; // 起点距离为 0

        // nodeToHasVisitedInColor[color][node]：是否 以 color 色边结尾 访问过 node
        boolean[][] nodeToHasVisitedInColor = new boolean[2][nodeAmount];

        // 队列：{node, lastColor, distance}
        Queue<int[]> stateSimpleQueue = new LinkedList<>();
        // 起始状态：可以 从红或蓝 开始（距离为 0）
        stateSimpleQueue.offer(new int[]{0, RED, 0});
        stateSimpleQueue.offer(new int[]{0, BLUE, 0});

        nodeToHasVisitedInColor[RED][0] = true;
        nodeToHasVisitedInColor[BLUE][0] = true;

        while (!stateSimpleQueue.isEmpty()) {
            int[] currentState = stateSimpleQueue.poll();
            int currentNode = currentState[0];
            int lastEdgesColor = currentState[1];
            int pathLength = currentState[2];

            // 下一条边 必须是 oppositeColor
            int nextEdgesColor = 1 - lastEdgesColor; // RED<->BLUE 切换

            // 遍历所有 nextEdgesColor 色边的邻居
            for (int currentNeighborNode : currentNodeToItsColoredNeighbors[nextEdgesColor][currentNode]) {
                if (!nodeToHasVisitedInColor[nextEdgesColor][currentNeighborNode]) {
                    nodeToHasVisitedInColor[nextEdgesColor][currentNeighborNode] = true;

                    // 更新答案（第一次到达即最短）
                    if (nodeToShortestAlternativePathReachIt[currentNeighborNode] == -1) {
                        nodeToShortestAlternativePathReachIt[currentNeighborNode] = pathLength + 1;
                    }

                    stateSimpleQueue.offer(new int[]{currentNeighborNode, nextEdgesColor, pathLength + 1});
                }
            }
        }

        return nodeToShortestAlternativePathReachIt;
    }
}
