package com.henry.tryout.leetcodes.Huawei.phase1.array.IPO_502.exe;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_qianwen_sortThenPollFromPQ {
    public int findMaximizedCapital(int maxAllowedProjectAmount,
                                    int currentNetBalance,
                                    int[] projectNoToItsProfit,
                                    int[] projectNoToItsCost) {
        int allProjectAmount = projectNoToItsProfit.length;

        /* 步骤1: 将 项目 按 其所需的成本/投资金额 升序排列 */
        // 🐖 使用 索引数组 来 避免修改 原数组
        Integer[] projectNoArrSortByCostInAsc = new Integer[allProjectAmount];
        // 元素初始化
        for (int currentProjectNo = 0; currentProjectNo < allProjectAmount; currentProjectNo++) {
            projectNoArrSortByCostInAsc[currentProjectNo] = currentProjectNo;
        }
        // 把元素 按照 项目成本 来 升序排序
        Arrays.sort(projectNoArrSortByCostInAsc, Comparator.comparingInt(projectNo -> projectNoToItsCost[projectNo]));

        // 准备一个 最大队列   用于 存储 所有‘当前可启动项目’的利润
        // 🐖 Java的默认实现 是 最小队列，所以需要传入 自定义的lambda比较器 来 得到最大队列
        PriorityQueue<Integer> projectProfitMaxQueue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        int currentProjectCursor = 0; // 指向 下一个未处理的项目（按 capital 排序后）

        // 最多进行 k轮投资 - 每轮投资都 贪心地 获取最大利润
        for (int currentRound = 0; currentRound < maxAllowedProjectAmount; currentRound++) {
            /* 步骤2：计算出 本轮投资 所能得到的最大利润 */
            // 手段：获取到 本轮投资 所有能够投资的所有项目 的利润，取其中的最大利润（使用最大队列）。
            while (currentProjectCursor < allProjectAmount && // 条件① 项目指针 在允许范围内
                    projectNoToItsCost[projectNoArrSortByCostInAsc[currentProjectCursor]] <= currentNetBalance) { // 条件② 手上的净余额 超过 当前所选的项目的成本
                // 获取到 当前可选择项目的利润
                int currentSelectableProjectsProfit = projectNoToItsProfit[projectNoArrSortByCostInAsc[currentProjectCursor]];
                // 把 该项目的利润 作为队列元素 添加到 优先队列中    用于 后继快速获取到 所有可选项目中 利润最大的项目
                projectProfitMaxQueue.offer(currentSelectableProjectsProfit);
                // 把 项目指针 向后移动一个位置  用于继续检查 下一个所需投资最小的项目 是不是 也是可选择的
                currentProjectCursor++;
            }

            // 如果 当前 已经没有 任何”可投资的项目“，说明 无法继续投资了，
            if (projectProfitMaxQueue.isEmpty()) {
                // 则：提前结束   防止poll()时引发NPE
                break;
            }

            /* 步骤3：（计算出 本轮投资 所能得到的最大利润 后）把 该利润 累加到 净余额中 */
            // 手段：从所有（本轮投资）可选的项目中，选择 利润最大的项目 来 获取利润（贪心策略）。
            // 并 把 该净利润 累计到 手上的净余额 中
            currentNetBalance += projectProfitMaxQueue.poll();
        }

        // 步骤4：返回 贪心策略（最大利润）下，最终 手中的净余额
        return currentNetBalance;
    }
}
