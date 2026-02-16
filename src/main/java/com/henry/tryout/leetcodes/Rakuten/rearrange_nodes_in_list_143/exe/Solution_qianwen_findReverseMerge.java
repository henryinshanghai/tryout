package com.henry.tryout.leetcodes.Rakuten.rearrange_nodes_in_list_143.exe;

public class Solution_qianwen_findReverseMerge {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: 找中点（使用快慢指针）
        ListNode slowCursor = head;
        ListNode fastCursor = head;

        // 条件：快指针还能走两步 停在一个预期节点上
        while (notReachToEnd(fastCursor.next) && notReachToEnd(fastCursor.next.next)) {
            slowCursor = slowCursor.next;
            fastCursor = fastCursor.next.next;
        } /* （这种写法）在循环结束时，慢指针 会停在前中点的位置 */

        /* 得到两个 子链表 */
        // 为 第二个子链表的头节点 添加一个头指针变量
        // 作用：用于引用 第二个子链表
        ListNode secondHalf = slowCursor.next;
        // 为 第一个链表的尾节点（slowCursor）的next 绑定null
        // 作用：用于断开两个子链表
        slowCursor.next = null;

        // Step 2: 反转后半段
        ListNode reversedSecond = reverseList(secondHalf);

        // Step 3: 交替合并 两个子链表中的节点
        mergeNodeInLists(head, reversedSecond);
    }

    // 辅助函数：反转链表
    private ListNode reverseList(ListNode head) {
        // 准备一组指针：prev（初始指向一个nil节点）、curr
        // 用于 改变curr的next指向（从 previousNext 到 prev）
        ListNode prev = null;
        ListNode currentNodeCursor = head;

        // 准备一个next指针   用于在 改变curr.next的指向 之前，记录下 当前next所指向的节点
        ListNode itsNextNode;

        while (notReachToEnd(currentNodeCursor)) {
            // ① 移动 next指针 到 当前节点指针 所指向的节点 的下一个节点
            itsNextNode = currentNodeCursor.next;

            // ② 改变当前节点的next指向（到prev）
            currentNodeCursor.next = prev;

            /* ③ 把指针组（prev、curr）向后移动一个位置 */
            // 移动 prev 到 curr 上
            prev = currentNodeCursor;
            // 把 curr 移动到 next 上
            currentNodeCursor = itsNextNode;
        }

        // 返回 反转后的 链表头节点
        return prev;
    }

    // 辅助函数：交替合并 两个链表中的节点（类似于 归并操作）
    private void mergeNodeInLists(ListNode l1Cursor, ListNode l2Cursor) {
        while (notReachToEnd(l1Cursor) && notReachToEnd(l2Cursor)) {
            // ① 记录下 两个链表中 当前curr的next节点
            ListNode l1OriginalNext = l1Cursor.next;
            ListNode l2OriginalNext = l2Cursor.next;

            // ② 对节点 按需重新连接：l1 -> l2 -> l1.next
            l1Cursor.next = l2Cursor;
            l2Cursor.next = l1OriginalNext;

            // ③ 把 curr游标指针 移动到 链表的next节点 上
            l1Cursor = l1OriginalNext;
            l2Cursor = l2OriginalNext;
        } /* （循环结束后）如果 l1 还有剩余（奇数长度），它已经在正确位置，无需处理 */
    }

    private boolean notReachToEnd(ListNode nodeCursorInL1) {
        return nodeCursorInL1 != null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}