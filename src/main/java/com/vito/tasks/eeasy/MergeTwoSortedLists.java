package com.vito.eeasy;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        var head = new ListNode();
        var tail = head;

        while (l1 != null || l2 != null) {

            long val1 = l1 != null ? l1.val : Long.MAX_VALUE;
            long val2 = l2 != null ? l2.val : Long.MAX_VALUE;

            if (val1 <= val2) {
                tail.next = l1;
                tail = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                tail = l2;
                l2 = l2.next;
            }
        }

        tail.next = null;
        return head.next;
    }
}
