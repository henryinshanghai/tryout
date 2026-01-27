package com.henry.tryout.leetcodes.Huawei.phase1.linkedlist.merge_sorted_list_21.exe;

// 递归的依据：链表 = 头节点 + 由剩余节点组成的子链表
public class Solution_qianwen_recursion {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /* 基础情况 */
        // 如果 存在有 其中一个链表为空，
        // 则：直接返回另一个（因为已经是有序链表了）
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        /* 递归地解决 子问题，并使用 子问题的结果 帮助解决 原始问题 */
        // 比较 两个子链表的头节点
        // 如果 链表1的头节点 较小，说明 结果链表的头节点 就是 链表1的头节点，则：
        if (list1.val <= list2.val) {
            // ① 确定 结果链表的头节点；
            // ② 对 链表1的剩余节点所组成的子链表 与 链表2 继续递归地合并；
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
