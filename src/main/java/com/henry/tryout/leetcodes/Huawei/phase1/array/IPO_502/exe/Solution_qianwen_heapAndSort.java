package com.henry.tryout.leetcodes.Huawei.phase1.array.IPO_502.exe;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_qianwen_heapAndSort {
    public int findMaximizedCapital(int maxAllowedProjectAmount,
                                    int currentNetBalance,
                                    int[] projectNoToItsProfit,
                                    int[] projectNoToItsCost) {
        int allProjectAmount = projectNoToItsProfit.length;

        // 步骤1: 将 项目 按 其所需的成本 升序排列
        // 🐖 使用 索引数组 来 避免修改 原数组
        Integer[] projectNoArrSortByCostInAsc = new Integer[allProjectAmount];
        // 元素初始化
        for (int currentProjectNo = 0; currentProjectNo < allProjectAmount; currentProjectNo++) {
            projectNoArrSortByCostInAsc[currentProjectNo] = currentProjectNo;
        }
        // 把元素 按照 项目成本 来 升序排序
        Arrays.sort(projectNoArrSortByCostInAsc, Comparator.comparingInt(projectNo -> projectNoToItsCost[projectNo]));

        // 步骤2: 准备一个 最大队列   用于 存储 所有‘当前可启动项目’的利润
        // 🐖 Java的默认实现是 最小队列，所以需要传入 自定义的lambda比较器
        PriorityQueue<Integer> projectProfitMaxQueue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        int currentProjectCursor = 0; // 指向 下一个未处理的项目（按 capital 排序后）

        // 步骤3: 最多进行 k轮投资 - 每轮投资都贪心地获取 最大利润
        for (int currentRound = 0; currentRound < maxAllowedProjectAmount; currentRound++) {
            /* 计算本轮投资的最大利润  手段：获取到 本轮投资 所有能够投资的项目的利润，取其中的最大利润（最大队列）。 */
            while (currentProjectCursor < allProjectAmount && // 条件① 项目指针 在允许范围内
                    projectNoToItsCost[projectNoArrSortByCostInAsc[currentProjectCursor]] <= currentNetBalance) { // 条件② 手上的净余额 超过 当前所选的项目的成本
                // 获取到 当前所选项目的利润
                int currentSelectedProjectsProfit = projectNoToItsProfit[projectNoArrSortByCostInAsc[currentProjectCursor]];
                // 把 该项目的利润 作为队列元素 添加到 优先队列中    用于 后继快速获取到 所有可选项目中 利润最大的项目
                projectProfitMaxQueue.offer(currentSelectedProjectsProfit);
                // 把 项目指针 向后移动一个位置  用于判断 下一个项目 是不是 也是可选的
                currentProjectCursor++;
            }

            // 如果 当前已经 没有 任何”可启动的项目“，说明 无法继续投资了，
            if (projectProfitMaxQueue.isEmpty()) {
                // 则：提前结束   防止poll()时引发NPE
                break;
            }

            // 当前次投资，从所有可选项目中，选择 利润最大的项目 来 获取利润（贪心策略）。并 把 该利润 累计到 手上的净余额中
            currentNetBalance += projectProfitMaxQueue.poll();
        }

        // 步骤4：返回 贪心策略（最大利润）下，最终 手中的净余额
        return currentNetBalance;
    }
}
