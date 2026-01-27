package com.henry.tryout.leetcodes.Huawei.phase2.arr.majority_element_229.exe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_qianwen_hashmap {
    public List<Integer> majorityElement(int[] nums) {
        int numAmount = nums.length;
        // 统计 数字的出现频次
        Map<Integer, Integer> numToItsFrequency = new HashMap<>();

        for (int currentNum : nums) {
            numToItsFrequency.put(currentNum,
                    numToItsFrequency.getOrDefault(currentNum, 0) + 1);
        }

        // 筛选出 那些个 频次超过 1/3的数字（最多只会有两个），并 添加进列表
        List<Integer> realMajorityList = new ArrayList<>();
        for (int currentNum : numToItsFrequency.keySet()) {
            if (numToItsFrequency.get(currentNum) > numAmount / 3) {
                realMajorityList.add(currentNum);
            }
        }

        // 返回所有众数的列表
        return realMajorityList;
    }
}
