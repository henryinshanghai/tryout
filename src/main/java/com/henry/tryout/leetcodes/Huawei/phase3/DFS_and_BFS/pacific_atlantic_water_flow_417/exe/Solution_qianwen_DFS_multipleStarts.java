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

        // 为全局变量赋值
        globalHeights = currentGridToItsHeights;

        int rowsAmount = currentGridToItsHeights.length;
        int columnAmount = currentGridToItsHeights[0].length;

        // 准备两个boolean二维矩阵  用作标记矩阵
        boolean[][] currentSpotToIsAbleFlowToPacific = new boolean[rowsAmount][columnAmount];
        boolean[][] currentSpotToIsAbleFlowToAtlantic = new boolean[rowsAmount][columnAmount];

        /* 🌊 从 太平洋边界 开始 DFS（上边界 + 左边界）*/
        // 作用：填充 标记矩阵，得到 哪些方格 由太平洋逆流可达
        for (int currentRow = 0; currentRow < rowsAmount; currentRow++) {
            dfs(currentRow,
                    0,
                    currentSpotToIsAbleFlowToPacific,
                    currentGridToItsHeights[currentRow][0]);
        }
        for (int currentColumn = 0; currentColumn < columnAmount; currentColumn++) {
            dfs(0,
                    currentColumn,
                    currentSpotToIsAbleFlowToPacific,
                    currentGridToItsHeights[0][currentColumn]);
        }

        /* 🌊 从大西洋边界开始 DFS（下边界 + 右边界）*/
        // 作用：填充 标记矩阵，得到 哪些方格 由大西洋逆流可达
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

        /* 🔍 从 太平洋的标记矩阵 与 大西洋的标记矩阵 中 找交集 */
        List<List<Integer>> allValidGridList = new ArrayList<>();
        for (int currentRow = 0; currentRow < rowsAmount; currentRow++) {
            for (int currentColumn = 0; currentColumn < columnAmount; currentColumn++) {
                if (currentSpotToIsAbleFlowToPacific[currentRow][currentColumn]
                        && currentSpotToIsAbleFlowToAtlantic[currentRow][currentColumn]) {
                    // 收集 既能由太平洋逆流到达、又能由大西洋逆流到达的方格
                    allValidGridList.add(Arrays.asList(currentRow, currentColumn));
                }
            }
        }

        return allValidGridList;
    }

    /**
     * 从 (x, y) 开始 DFS，标记 所有 能“倒流”到的方格
     *
     * @param currentSpotX            当前位置的x坐标
     * @param currentSpotY            当前位置的y坐标
     * @param currentSpotToHasVisited 标记矩阵（pacific 或 atlantic）
     * @param prevHeight              上一个位置的高度（用于比较）
     */
    private void dfs(int currentSpotX,
                     int currentSpotY,
                     boolean[][] currentSpotToHasVisited,
                     int prevHeight) { // 🐖 这个参数 可以通过gongshui的写法省略掉
        // 🛑 边界检查  + 重复访问检查 + 业务检查（因此需要参数prevHeight）
        if (currentSpotX < 0 || currentSpotX >= globalHeights.length ||
                currentSpotY < 0 || currentSpotY >= globalHeights[0].length ||
                currentSpotToHasVisited[currentSpotX][currentSpotY] ||
                // ⚠ 注意：必须 >= 才能流（倒流时 要求 不下降）
                globalHeights[currentSpotX][currentSpotY] < prevHeight) {
            return;
        }

        // ✅ 标记 当前位置 为 '已访问'/可达
        currentSpotToHasVisited[currentSpotX][currentSpotY] = true;

        // 🔁 向 当前位置的四个邻居方向 继续搜索
        for (int[] currentDirection : DIRS) {
            dfs(
                    currentSpotX + currentDirection[0],
                    currentSpotY + currentDirection[1],
                    currentSpotToHasVisited,
                    globalHeights[currentSpotX][currentSpotY]);
        }
    }
}
