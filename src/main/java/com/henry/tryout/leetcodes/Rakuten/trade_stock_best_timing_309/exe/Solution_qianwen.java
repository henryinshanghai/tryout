package com.henry.tryout.leetcodes.Rakuten.trade_stock_best_timing_309.exe;

public class Solution_qianwen {
    public int maxProfit(int[] currentDayToItsStockPrice) {
        if (currentDayToItsStockPrice == null || currentDayToItsStockPrice.length <= 1) {
            return 0; // 无法交易
        }

        int days = currentDayToItsStockPrice.length;

        // 定义三个 DP 数组，记录每一天结束时 处于 不同状态 的最大利润
        // currentDayToItsNetBalanceInHold[i]   : 第 i 天结束时 **持有股票** 的最大利润
        // currentDayToItsNetBalanceInJustSold[i]   : 第 i 天结束时 **不持有股票 且 第二天不可买入（刚刚卖出股票）** 的最大利润
        // currentDayToItsNetBalanceInCanBuy[i]   : 第 i 天结束时 **不持有股票 且 第二天可买入 ** 的最大利润
        int[] currentDayToItsNetBalanceInHold = new int[days];
        int[] currentDayToItsNetBalanceInJustSold = new int[days];
        int[] currentDayToItsNetBalanceInCanBuy = new int[days];

        // ========== 初始化第 0 天（第一天）的状态 ==========
        currentDayToItsNetBalanceInHold[0] = -currentDayToItsStockPrice[0]; // 第一天买入，利润为 -price[0]
        currentDayToItsNetBalanceInJustSold[0] = 0;          // 第一天不可能卖出（没有股票可卖），设为 0（安全值）
        currentDayToItsNetBalanceInCanBuy[0] = 0;          // 第一天什么都不做，利润为 0

        // ========== 从第 1 天开始，逐天更新状态 ==========
        for (int currentDay = 1; currentDay < days; currentDay++) {
            // 状态1: 第 currentDay 天结束时持有股票
            // 有两种可能：
            //   a) 昨天 是 持有状态，今天 什么都不做 → currentDayToItsNetBalanceInHold[currentDay-1]
            //   b) 昨天处于 currentDayToItsNetBalanceInCanBuy 状态（可买入），今天 买入 → currentDayToItsNetBalanceInCanBuy[currentDay-1] - prices[currentDay]
            currentDayToItsNetBalanceInHold[currentDay] =
                    Math.max(currentDayToItsNetBalanceInHold[currentDay - 1],
                            currentDayToItsNetBalanceInCanBuy[currentDay - 1] - currentDayToItsStockPrice[currentDay]);

            // 状态2: 第 currentDay 天结束时 刚刚卖出股票
            // 只有一种可能：
            //   昨天 持有股票，今天 卖出 → currentDayToItsNetBalanceInHold[currentDay-1] + prices[currentDay]
            currentDayToItsNetBalanceInJustSold[currentDay] =
                    currentDayToItsNetBalanceInHold[currentDay - 1] + currentDayToItsStockPrice[currentDay];

            // 状态3: 第 currentDay 天结束时 空仓 且 可买入（不在冷冻期）
            // 有两种可能：
            //   a) 昨天 是 currentDayToItsNetBalanceInCanBuy 状态，今天 什么都不做 → currentDayToItsNetBalanceInCanBuy[currentDay-1]
            //   b) 昨天刚卖出（currentDayToItsNetBalanceInJustSold[currentDay-1]），今天是 什么都不做（自动变为 可买入状态） → currentDayToItsNetBalanceInJustSold[currentDay-1]
            currentDayToItsNetBalanceInCanBuy[currentDay] =
                    Math.max(currentDayToItsNetBalanceInCanBuy[currentDay - 1],
                            currentDayToItsNetBalanceInJustSold[currentDay - 1]);
        }

        // ========== 最终答案 ==========
        // 最后一天，我们 肯定不想 还拿着股票（因为没卖出 就 赚不到钱）
        // 所以答案是 最后一天 处于 "刚卖出" 或 "可买入" 状态的最大利润
        return Math.max(currentDayToItsNetBalanceInJustSold[days - 1], currentDayToItsNetBalanceInCanBuy[days - 1]);
    }
}
