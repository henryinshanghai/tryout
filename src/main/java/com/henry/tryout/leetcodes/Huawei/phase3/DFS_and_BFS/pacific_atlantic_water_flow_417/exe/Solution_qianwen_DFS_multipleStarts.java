package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.pacific_atlantic_water_flow_417.exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_qianwen_DFS_multipleStarts {
    // 使用二维矩阵 来 标识4个方向
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // 使用成员变量 来 减少递归方法的参数
    private static int[][] globalHeights;

    public List<List<Integer>> pacificAtlantic(int[][] currentGridToItsHeights) {
        if (currentGridToItsHeights == null || currentGridToItsHeights.length == 0) {
            return new ArrayList<>();
        }

        globalHeights = currentGridToItsHeights;
        int rowsAmount = currentGridToItsHeights.length, columnAmount = currentGridToItsHeights[0].length;
        boolean[][] currentSpotToIsAbleFlowToPacific = new boolean[rowsAmount][columnAmount];
        boolean[][] currentSpotToIsAbleFlowToAtlantic = new boolean[rowsAmount][columnAmount];

        // 🌊 从 太平洋边界 开始 DFS（上边界 + 左边界）
        for (int currentRow = 0; currentRow < rowsAmount; currentRow++) {
            dfs(
                    currentRow,
                    0,
                    currentSpotToIsAbleFlowToPacific,
                    currentGridToItsHeights[currentRow][0]);
        }
        for (int currentColumn = 0; currentColumn < columnAmount; currentColumn++) {
            dfs(
                    0,
                    currentColumn,
                    currentSpotToIsAbleFlowToPacific,
                    currentGridToItsHeights[0][currentColumn]);
        }

        // 🌊 从大西洋边界开始 DFS（下边界 + 右边界）
        for (int currentRow = 0; currentRow < rowsAmount; currentRow++) {
            dfs(
                    currentRow,
                    columnAmount - 1,
                    currentSpotToIsAbleFlowToAtlantic,
                    currentGridToItsHeights[currentRow][columnAmount - 1]);
        }
        for (int currentColumn = 0; currentColumn < columnAmount; currentColumn++) {
            dfs(
                    rowsAmount - 1,
                    currentColumn,
                    currentSpotToIsAbleFlowToAtlantic,
                    currentGridToItsHeights[rowsAmount - 1][currentColumn]);
        }

        // 🔍 找交集
        List<List<Integer>> allValidGridList = new ArrayList<>();
        for (int currentRow = 0; currentRow < rowsAmount; currentRow++) {
            for (int currentColumn = 0; currentColumn < columnAmount; currentColumn++) {
                if (currentSpotToIsAbleFlowToPacific[currentRow][currentColumn]
                        && currentSpotToIsAbleFlowToAtlantic[currentRow][currentColumn]) {
                    allValidGridList.add(Arrays.asList(currentRow, currentColumn));
                }
            }
        }

        return allValidGridList;
    }

    /**
     * 从 (x, y) 开始 DFS，标记 所有 能“倒流”到的方格
     *
     * @param currentSpotX              当前位置的x坐标
     * @param currentSpotY              当前位置的y坐标
     * @param currentSpotToHasVisited   标记矩阵（pacific 或 atlantic）
     * @param prevHeight                上一个位置的高度（用于比较）
     */
    private void dfs(int currentSpotX,
                     int currentSpotY,
                     boolean[][] currentSpotToHasVisited,
                     int prevHeight) { // 🐖 这个参数 可以通过gongshui的写法省略掉
        // 🛑 边界检查 + 高度检查 + 重复访问检查
        if (currentSpotX < 0 || currentSpotX >= globalHeights.length ||
                currentSpotY < 0 || currentSpotY >= globalHeights[0].length ||
                currentSpotToHasVisited[currentSpotX][currentSpotY] ||
                globalHeights[currentSpotX][currentSpotY] < prevHeight) { // ⚠️ 注意：必须 >= 才能流（倒流时 要求 不下降）
            return;
        }

        currentSpotToHasVisited[currentSpotX][currentSpotY] = true; // ✅ 标记为'已访问'/可达

        // 🔁 向四个方向继续搜索
        for (int[] currentDirection : DIRS) {
            dfs(
                    currentSpotX + currentDirection[0],
                    currentSpotY + currentDirection[1],
                    currentSpotToHasVisited,
                    globalHeights[currentSpotX][currentSpotY]);
        }
    }
}
