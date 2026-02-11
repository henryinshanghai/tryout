package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.pacific_atlantic_water_flow_417.exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_gongshui_DFS {

    int colAmount, rowAmount;
    int[][] globalHeights;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        globalHeights = heights;
        rowAmount = globalHeights.length;
        colAmount = globalHeights[0].length;
        boolean[][] currentSpotToIsAbleToFlowFromPacific = new boolean[rowAmount][colAmount],
                currentSpotToIsAbleToFlowFromAtlantic = new boolean[rowAmount][colAmount];

        // 太平洋
        for(int currentRow = 0; currentRow < rowAmount; currentRow++) {
            dfs(currentRow, 0, currentSpotToIsAbleToFlowFromPacific);
        }

        for(int currentCol = 0; currentCol < colAmount; currentCol++) {
            dfs(0, currentCol, currentSpotToIsAbleToFlowFromPacific);
        }

        // 大西洋
        for(int currentRow = 0; currentRow < rowAmount; currentRow++) {
            dfs(currentRow, colAmount - 1, currentSpotToIsAbleToFlowFromPacific);
        }

        for(int currentCol = 0; currentCol < colAmount; currentCol++) {
            dfs(rowAmount - 1, currentCol, currentSpotToIsAbleToFlowFromPacific);
        }

        /* 在得到了 ‘由太平洋方格可达的所有方格’ 与 ‘由大西洋方格可达的所有方格’后，查看 方格集合的交集 */
        List<List<Integer>> allValidGridList = new ArrayList<>();
        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                // 如果 当前位置 既能 从太平洋流入，又能 从大西洋流入，
                if (currentSpotToIsAbleToFlowFromPacific[currentRow][currentCol] &&
                        currentSpotToIsAbleToFlowFromAtlantic[currentRow][currentCol]) {
                    // 则：把当前位置 添加到 结果集中
                    allValidGridList.add(Arrays.asList(currentRow, currentCol));
                }
            }
        }
        return allValidGridList;
    }

    int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    void dfs(int currentSpotX, int currentSpotY, boolean[][] currentSpotToIsAbleToFlowFromSea) {
        // 标记 当前方格
        currentSpotToIsAbleToFlowFromSea[currentSpotX][currentSpotY] = true;

        // 对于当前方格在所有方向上的邻居...
        for (int[] currentDirection : DIRS) {
            // 计算该邻居的坐标
            int neighborSpotX = currentSpotX + currentDirection[0],
                    neighborSpotY = currentSpotY + currentDirection[1];

            // 如果 该邻居越界，则：
            if (neighborSpotX < 0 ||
                    neighborSpotX >= rowAmount ||
                    neighborSpotY < 0 ||
                    neighborSpotY >= colAmount) { // 越界检查
                // 不再进行后继处理
                continue;
            }

            // 如果 该邻居已经被标记过了 或者 该邻居的高度 小于 当前方格的高度，说明 当前方向上的递归结束，则：
            if (currentSpotToIsAbleToFlowFromSea[neighborSpotX][neighborSpotY] || // 重复性检查
                    globalHeights[neighborSpotX][neighborSpotY] < globalHeights[currentSpotX][currentSpotY]) { // 业务条件检查
                // 不再进行后继处理
                continue;
            }

            // 否则，以该邻居为起点 继续递归地进行DFS
            dfs(neighborSpotX, neighborSpotY, currentSpotToIsAbleToFlowFromSea);
        }
    }
}
