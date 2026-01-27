package com.henry.tryout.leetcodes.Huawei.phase1.linkedlist.remove_duplicates_83.exe;

public class Solution_qianwen_unlinkDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        // 边界处理：空链表 或 单节点 无需去重
        if (head == null || head.next == null) {
            return head;
        }

        // 从头开始遍历
        ListNode currentNodeCursor = head;

        // 只要还有 下一个节点，就 继续检查
        while (currentNodeCursor != null &&
                currentNodeCursor.next != null) {
            // 如果 当前节点的val 与 其下一个节点的val 相等，说明 出现了重复元素，则：
            if (currentNodeCursor.val == currentNodeCursor.next.val) {
                // 直接跳过 该重复的节点  手段：借助next指针
                currentNodeCursor.next = currentNodeCursor.next.next;
                // 🐖 currentNodeCursor 不移动！继续检查 新的 currentNodeCursor.next
            } else {
                // 如果 没有出现重复节点，则：把 游标指针 前进到 其下一个节点处
                currentNodeCursor = currentNodeCursor.next;
            }
        }

        // 头节点指针 一直没有移动，因此返回 头节点
        // 🐖 头节点 不会被删（除非 全删，但至少 留一个）
        return head;
    }
}