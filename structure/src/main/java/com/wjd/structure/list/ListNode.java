package com.wjd.structure.list;

/**
 * 链表节点
 *
 * @author weijiaduo
 * @since 2023/10/29
 */
public class ListNode {

    /**
     * 节点值
     */
    public int val;
    /**
     * 前指针
     */
    public ListNode prev;
    /**
     * 后指针
     */
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
    }

    public static ListNode build(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1), tail = dummy;
        for (int val : values) {
            ListNode node = new ListNode(val);
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        ListNode head = dummy.next;
        head.prev = null;
        dummy.next = null;
        return head;
    }

    public static String toString(ListNode list) {
        if (list == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        ListNode p = list;
        while (p != null) {
            sb.append(p.val).append(", ");
            p = p.next;
            if (p == list) {
                break;
            }
        }
        sb.setLength(sb.length() - 2);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

}
