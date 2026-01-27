package com.henry.tryout.leetcodes.Huawei.phase1.linkedlist.palindrome_linkedList_234.exe;

public class Solution_qianwen_findFrontMiddle {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 步骤1: 快慢指针 找到 前半段的最后一个节点（前中点）
        // 🐖 这是模板代码
        ListNode slowCursor = head;
        ListNode fastCursor = head;
        while (fastCursor.next != null && fastCursor.next.next != null) {
            slowCursor = slowCursor.next;
            fastCursor = fastCursor.next.next;
        } /* 循环结束后，slowCursor 会停在 前半段的最后一个节点上 */

        // 步骤2: 反转 后半段
        ListNode secondHalf = reverse(slowCursor.next);
        slowCursor.next = null; // 断开前后两段（可选，便于理解）

        // 步骤3: 比较 前半段 和 反转后的后半段
        ListNode cursorOfFirstHalf = head;
        ListNode cursorOfSecondHalf = secondHalf;

        boolean isPalindrome = true;
        while (cursorOfSecondHalf != null) {
            // 只要 当前指针 所指向的节点的val 不相同，说明 链表不是 palindrome，则：
            if (cursorOfFirstHalf.val != cursorOfSecondHalf.val) {
                // 把flag标记为false
                isPalindrome = false;
                // 跳出循环
                break;
            }

            // 同时向后移动 前半段指针、后半段指针
            cursorOfFirstHalf = cursorOfFirstHalf.next;
            cursorOfSecondHalf = cursorOfSecondHalf.next;
        }

        // 步骤4（可选）: 恢复链表
        slowCursor.next = reverse(secondHalf);

        return isPalindrome;
    }

    // 反转链表的标准写法 - 使用一组指针(prev, curr) 来 反转节点的连接方向
    private ListNode reverse(ListNode head) {
        // 设置一个dummy节点（null），使prev指针 初始化指向它
        ListNode prev = null;
        // curr指针 初始化指向 头节点
        ListNode curr = head;

        while (curr != null) {
            // 记录下 当前节点的next节点
            ListNode next = curr.next;
            // 改变 当前节点的指向（从指向next 改变为 指向prev）
            curr.next = prev;

            // 把这组指针（prev, curr） 向后移动一个位置
            prev = curr;
            curr = next;
        } /* 循环结束后，prev 指向 原始链表中的最后一个节点，curr指针 指向 null */

        // 返回prev指针所指向的节点（也就是 反转后的链表的头节点）
        return prev;
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