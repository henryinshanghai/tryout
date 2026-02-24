package com.henry.tryout.leetcodes.Huawei.phase2.arr.majority_element_229.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_BMVote {
    public List<Integer> majorityElement(int[] nums) {
        // 初始化两个候选者（使用 Integer 而非 int，以便用 null 表示“尚未设置该众数”）
        Integer currentAssumeMajority1 = null;
        Integer currentAssumeMajority2 = null;

        // 初始化两个候选者的计数器
        int majority1VoteAmount = 0;
        int majority2VoteAmount = 0;

        // ========================
        // 阶段一：投票 筛选 候选者
        // ========================
        for (int currentNum : nums) {
            /* 情形1：与 某个‘假定众数’相等 */
            if (currentAssumeMajority1 != null &&
                    currentNum == currentAssumeMajority1) {
                // 加票
                majority1VoteAmount++;
            } else if (currentAssumeMajority2 != null &&
                    currentNum == currentAssumeMajority2) {
                majority2VoteAmount++;
            } else if (majority1VoteAmount == 0) { /* 情形2：与 两个‘假定众数’都不相等，且 当前票数为0 */
                // 替换‘假定众数’
                currentAssumeMajority1 = currentNum;
                majority1VoteAmount = 1; // 新候选者 初始 得1票
            } else if (majority2VoteAmount == 0) {
                currentAssumeMajority2 = currentNum;
                majority2VoteAmount = 1; // 新候选者 初始 得1票
            } else { /* 情形3：与 两个‘假定众数’都不相等，且 当前票数不为0 */
                // 减票
                majority1VoteAmount--;
                majority2VoteAmount--;
            }
        } /* 循环结束后，会得到两个‘可能的众数’ */

        // ========================
        // 阶段二：验证 候选者 是否真的 满足条件
        // 注意：投票阶段 只保证 “可能是”，不保证 “一定是”
        // ========================
        majority1VoteAmount = 0;
        majority2VoteAmount = 0;
        for (int currentNum : nums) {
            if (currentAssumeMajority1 != null &&
                    currentNum == currentAssumeMajority1) {
                majority1VoteAmount++;
            }
            if (currentAssumeMajority2 != null &&
                    currentNum == currentAssumeMajority2) {
                majority2VoteAmount++;
            }
        }

        // ========================
        // 收集 最终结果
        // ========================
        List<Integer> realMajorityList = new ArrayList<>();
        int numAmount = nums.length;

        // 只有 真实出现次数 > numAmount/3 的候选者(真正的标准) 才加入结果
        if (majority1VoteAmount > numAmount / 3) {
            realMajorityList.add(currentAssumeMajority1);
        }
        if (majority2VoteAmount > numAmount / 3) {
            realMajorityList.add(currentAssumeMajority2);
        }

        return realMajorityList;
    }
}
