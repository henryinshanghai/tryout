package com.henry.tryout.leetcodes.Rakuten.permutation_sequence_60.exe;

import java.util.ArrayList;
import java.util.List;

// 验证：① list对象，在 删除 其某个位置上的元素 后，其他的元素会自动补位;
// ② list对象删除 指定位置上的元素 - list.remove(given_index)
public class ArrayListRemoveDemo {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();

        nums.add(0);
        nums.add(10);
        nums.add(20);
        nums.add(30);
        nums.add(40);
        nums.add(50);

        for (int currentSpot = 0; currentSpot < nums.size(); currentSpot++) {
            System.out.print("位置" + currentSpot + "上的元素为：" + nums.get(currentSpot) +  "; ");
        }
        System.out.println();

        // 删除index=2的元素
        nums.remove(2);
        for (int currentSpot = 0; currentSpot < nums.size(); currentSpot++) {
            System.out.print("位置" + currentSpot + "上的元素为：" + nums.get(currentSpot) + "; ");
        }
    }
}
