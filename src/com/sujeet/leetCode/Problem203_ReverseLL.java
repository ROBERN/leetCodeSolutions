package com.sujeet.leetCode;

public class Problem203_ReverseLL {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode res = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }

    public static void main(String[] args) {
        Problem203_ReverseLL obj = new Problem203_ReverseLL();
        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(6);
        obj.reverseList(head);
    }
}
