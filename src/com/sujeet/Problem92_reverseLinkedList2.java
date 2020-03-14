package com.sujeet;

public class Problem92_reverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || n == 1) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        int step = 1;
        ListNode before = dummy;

        while(before != null && step < m) {
            before = before.next;
            step++;
        }
        ListNode first = before.next;
        ListNode after = first;
        while(after!= null && step <= n) {
            after = after.next;
            step++;
        }

        ListNode rev = reverseTill(first, n-m+1);
        first.next = after;
        if(before != dummy) {
            before.next = rev;
        } else{
            return rev;
        }
        return head;
    }

    private ListNode reverseTill(ListNode head, int step) {
        if(head == null || head.next == null || step <= 1) {
            return head;
        }
        ListNode node = reverseTill(head.next, step-1);
        head.next.next = head;
        head.next = null;
        return node;
    }
}
