package com.henry.tryout.leetcodes.Huawei.phase2.arr.majority_element_229.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_BMVote {
    public List<Integer> majorityElement(int[] nums) {
        // 初始化两个候选者（使用 Integer 而非 int，以便用 null 表示“未设置”）
        Integer currentAssumeMajority1 = null;
        Integer currentAssumeMajority2 = null;

        // 初始化两个候选者的计数器
        int majority1sFrequency = 0;
        int majority2sFrequency = 0;

        // ========================
        // 阶段一：投票 筛选 候选者
        // ========================
        for (int currentNum : nums) {
            // 情况1：当前数字 等于 候选者1 → 候选者1 得一票
            if (currentAssumeMajority1 != null &&
                    currentNum == currentAssumeMajority1) {
                majority1sFrequency++;
            }
            // 情况2：当前数字 等于 候选者2 → 候选者2 得一票
            else if (currentAssumeMajority2 != null &&
                    currentNum == currentAssumeMajority2) {
                majority2sFrequency++;
            }
            // 情况3：候选者1的票数 已归零 → 用 当前数字 替换 候选者1
            else if (majority1sFrequency == 0) {
                currentAssumeMajority1 = currentNum;
                majority1sFrequency = 1; // 新候选者 初始 得1票
            }
            // 情况4：候选者2的票数 已归零 → 用 当前数字 替换 候选者2
            else if (majority2sFrequency == 0) {
                currentAssumeMajority2 = currentNum;
                majority2sFrequency = 1; // 新候选者 初始 得1票
            }
            // 情况5：当前数字 与 两个候选者 都不同，且 两个候选者 都有票
            // → 三者 互相抵消（各减一票），相当于“淘汰”一轮
            else {
                majority1sFrequency--;
                majority2sFrequency--;
            }
        }

        // ========================
        // 阶段二：验证 候选者 是否真的 满足条件
        // 注意：投票阶段 只保证 “可能是”，不保证 “一定是”
        // ========================
        majority1sFrequency = 0;
        majority2sFrequency = 0;
        for (int currentNum : nums) {
            if (currentAssumeMajority1 != null &&
                    currentNum == currentAssumeMajority1) {
                majority1sFrequency++;
            }
            if (currentAssumeMajority2 != null &&
                    currentNum == currentAssumeMajority2) {
                majority2sFrequency++;
            }
        }

        // ========================
        // 收集 最终结果
        // ========================
        List<Integer> realMajorityList = new ArrayList<>();
        int numAmount = nums.length;

        // 只有 真实出现次数 > numAmount/3 的候选者(真正的标准) 才加入结果
        if (majority1sFrequency > numAmount / 3) {
            realMajorityList.add(currentAssumeMajority1);
        }
        if (majority2sFrequency > numAmount / 3) {
            realMajorityList.add(currentAssumeMajority2);
        }

        return realMajorityList;
    }
}
