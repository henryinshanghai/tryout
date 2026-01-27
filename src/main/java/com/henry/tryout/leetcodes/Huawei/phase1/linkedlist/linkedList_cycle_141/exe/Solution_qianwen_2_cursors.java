package com.henry.tryout.leetcodes.Huawei.phase1.linkedlist.linkedList_cycle_141.exe;

public class Solution_qianwen_2_cursors {
    public boolean hasCycle(ListNode head) {
        // 最开始时，把快指针与慢指针 都指向 链表的头节点
        ListNode slowCursor = head, fastCursor = head;

        // 遍历链表中的节点（需要 确保不会出现 NPE）
        while (fastCursor != null && fastCursor.next != null) {
            // 慢指针 向前移动一个节点
            slowCursor = slowCursor.next;
            // 快指针 向前移动两个节点
            fastCursor = fastCursor.next.next;

            // 如果 慢指针 与 快指针 （在某一刻）指向了同一个节点，说明 链表中存在有环，则：
            if (slowCursor == fastCursor) {
                // 返回true 表示 链表中存在有环
                return true;
            }
        }

        // 如果 链表中的节点遍历完成，方法仍旧没有返回，说明 链表中 不存在有环，则：
        // 返回 false 表示 链表中没有存在环
        return false;
    }
}
