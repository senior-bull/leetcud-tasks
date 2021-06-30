package com.vito.med;

import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        ListNode start = new ListNode(0);
        ListNode tail = start;

        for(ListNode node : lists) {
            if(node != null) {
                priorityQueue.add(node);
            }
        }

        ListNode next;
        while((next = priorityQueue.poll()) != null) {
            tail.next = next;
            tail = next;

            if(next.next != null) {
                priorityQueue.add(next.next);
            }
        }

        return start.next;
    }
}
