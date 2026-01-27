package com.henry.tryout.leetcodes.Huawei.phase1.linkedlist.merge_sorted_list_21.exe;

public class Solution_qianwen_2_cursors {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 步骤1：创建 虚拟头节点     用于锚定结果链表的头节点
        ListNode dummyNode = new ListNode(-1);
        // 步骤2：准备一个cursorOfPioneerNode指针    用于逐步连接出 结果链表
        ListNode cursorOfPioneerNode = dummyNode;

        // 准备 待比较节点的指针
        ListNode cursorOfNodeToCompareInList1 = list1;
        ListNode cursorOfNodeToCompareInList2 = list2;

        // 双指针遍历，直到 其中一个链表 为空
        while (cursorOfNodeToCompareInList1 != null &&
                cursorOfNodeToCompareInList2 != null) {
            // 对 待比较的节点 进行比较...
            if (cursorOfNodeToCompareInList1.val <= cursorOfNodeToCompareInList2.val) {
                // ① 把 较小的节点 连接进 结果链表中
                cursorOfPioneerNode.next = cursorOfNodeToCompareInList1;
                // ② 把 较小节点 所在的链表中的 游标指针 向后移动一个位置
                cursorOfNodeToCompareInList1 = cursorOfNodeToCompareInList1.next;
            } else { // 步骤同上
                cursorOfPioneerNode.next = cursorOfNodeToCompareInList2;
                cursorOfNodeToCompareInList2 = cursorOfNodeToCompareInList2.next;
            }

            // ③（在 把 较小节点 连接进 结果链表中后）把 结果链表的游标指针 向后移动一个位置
            cursorOfPioneerNode = cursorOfPioneerNode.next;
        }

        // 将 仍有剩余节点的链表（节点指针非null） 直接连接到 结果链表上
        cursorOfPioneerNode.next =
                (cursorOfNodeToCompareInList1 != null)
                        ? cursorOfNodeToCompareInList1
                        : cursorOfNodeToCompareInList2;

        // 返回 结果链表的 头节点(也就是 dummy的下一个节点)
        return dummyNode.next;
    }
}