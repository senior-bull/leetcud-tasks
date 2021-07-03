package com.vito.eeasy;

public class RemoveNthNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel;
        ListNode curr = head;

        ListNode first = sentinel;
        for(int i = 0; i < n; i++) {
            first = first.next;
        }

        while(first.next != null) {
            prev = curr;
            curr = curr.next;
            first = first.next;
        }

        prev.next = curr.next;
        return sentinel.next;
    }
}
