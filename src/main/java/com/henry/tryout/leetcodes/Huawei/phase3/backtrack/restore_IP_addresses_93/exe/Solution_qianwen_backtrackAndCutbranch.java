package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.restore_IP_addresses_93.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_backtrackAndCutbranch {
    public List<String> restoreIpAddresses(String originalNumsStr) {
        // 准备一个list     用于存储所有有效的IP地址
        List<String> allValidIPList = new ArrayList<>();
        // 准备一个list     用于存储 当前有效的IP地址
        List<String> validIPAddress = new ArrayList<>();

        backtrack(originalNumsStr, 0, validIPAddress, allValidIPList);
        return allValidIPList;
    }

    /**
     * 回溯搜索 所有可能的 有效IP地址 分割方案
     * 手段：从 数字指针位置 开始，分别选择 1个字符、2个字符、3个字符 来 作为 所构造IP的当前段。以此方式 来 构造出有效IP地址
     *
     * @param originalNumsStr   原始的数字字符串
     * @param currentNumCharSpot    当前数字字符指针的位置（剩余可用字符的起始位置）
     * @param constructedIPAddress  当前已经构造出的IP地址
     * @param allValidIPAddressList 用于 存储所有可能的 有效IP地址的列表
     */
    private void backtrack(String originalNumsStr,
                           int currentNumCharSpot,
                           List<String> constructedIPAddress,
                           List<String> allValidIPAddressList) {
        /* 构造过程结束：已经选择了 4个IP段（IP地址 必须恰好 由4段组成） */
        // 如果 所构造的IP地址 现在 已经是4段，说明 IP地址 已经构造完成，
        if (constructedIPAddress.size() == 4) {
            /* 则：查看 当前切割方案 是不是 一个有效的方案 */
            // 如果 字符串中的所有数字字符 都已用尽（当前字符指针 指向 字符串末尾），
            // 说明 当前切割方案 是一个 有效的方案
            if (currentNumCharSpot == originalNumsStr.length()) {
                // 则：使用 .连接 列表中的所有段 来 得到有效的IP地址，并 把它添加到 结果列表中
                allValidIPAddressList.add(String.join(".", constructedIPAddress));
            }

            /* 返回给 上一级调用 来 继续尝试其他方案 */
            // ① 如果 原始字符串中的字符 用尽了，说明 找到了一个 有效的IP分割方案
            // ② 如果 原始字符串中的字符 没有用尽，说明 当前的IP分割方案 无效
            // 不管哪种情况，都该 返回给上一级调用
            return;
        }

        /* （在 继续构造IP地址剩余的段 之前）剪枝：剩余字符 太多 或 太少（无法得到有效的IP地址切割方案） */
        int remainingCharAmount = originalNumsStr.length() - currentNumCharSpot;
        int requiredSegments = 4 - constructedIPAddress.size();
        if (remainingCharAmount < requiredSegments // 情形1：剩余字符 太少(字符数量 < 所需段的数量)，不够用来 构造剩下的所有段
                || remainingCharAmount > requiredSegments * 3) { // 情形2：剩余字符 太多，即使 IP地址剩下的所有段 都使用3个数字，仍会 剩余数字
            return;
        }

        /* 从 currStartIndex位置开始，尝试构造出 当前IP地址中剩余的段 */
        // 尝试从 当前数字字符指针处，分别取 1 位数字、2 位数字、3 位数字 来 作为 当前的段
        for (int currentSegmentLength = 1;
             currentSegmentLength <= 3;
             currentSegmentLength++) {

            // 〇 防止越界：确保 数字指针 + 当前段的数字长度 不超过 字符串长度
            // 🐖 这个判断 也可以 && 添加到 for的判断中，效果相同
            if(currentNumCharSpot + currentSegmentLength > originalNumsStr.length()) {
                // 后续尝试 必然越界，因此 无需再试
                break;
            }

            // ① 获取到 当前所选择的 段字符串
            // 手段：截取 指定区间内的子字符串（左闭右开）
            String currentSegmentStr =
                    originalNumsStr.substring(currentNumCharSpot, currentNumCharSpot + currentSegmentLength);

            // ❌ 如果 segment 不合法（如 "256" 或 "01"），
            // 则：直接跳过，不再递归构造IP地址
            if (!isValid(currentSegmentStr)) {
                continue;
            }

            /* 如果 它是合法的段（符合段的3个约束），则：回溯三部曲 */
            // 把 所选的当前段 添加到 IP地址（段的列表）中
            constructedIPAddress.add(currentSegmentStr);
            // 基于 此选择的段，递归地 解决子问题（使用更短的字符串 构造 剩余的段）
            backtrack(originalNumsStr,
                    currentNumCharSpot + currentSegmentLength,
                    constructedIPAddress,
                    allValidIPAddressList); // 递归
            // 撤销 当前选择的段
            constructedIPAddress.remove(constructedIPAddress.size() - 1); // 撤销选择（回溯）
        }
    }

    // 检查一个段是否是有效的
    private boolean isValid(String segmentStr) {
        // ① 长度约束
        if (segmentStr.length() == 0 || segmentStr.length() > 3) {
            return false;
        }
        // ② 前导0约束
        if (segmentStr.charAt(0) == '0' && segmentStr.length() > 1) {
            return false;
        }
        // ③ 数值约束
        int num = Integer.parseInt(segmentStr);
        return num <= 255;
    }
}
