package com.henry.tryout.leetcodes.Huawei.phase1.linkedlist.linkedList_cycle_141.exe;

import java.util.HashSet;
import java.util.Set;

public class Solution_qianwen_hashset {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> visitedNodeSet = new HashSet<>();

        // 顺序遍历 链表中的节点
        while (head != null) {
            // 如果 当前节点 存在于 节点集合中，说明 遍历到了 一个已经遍历过的节点，也就是存在有环，则：
            if (visitedNodeSet.contains(head)) {
                // 返回 true 表示 存在有环
                return true;
            }

            // 如果 当前节点 不存在于 节点Set中，则：
            // ① 把 当前节点 添加到 节点Set中；
            visitedNodeSet.add(head);
            // ② 把 链表中的节点指针 向后移动一个位置
            head = head.next;
        }

        // 如果 链表遍历结束后 方法还没有return，说明 整个遍历过程 没有遍历已经访问过的节点，则：
        // 返回false，表示 不存在有环
        return false;
    }
}