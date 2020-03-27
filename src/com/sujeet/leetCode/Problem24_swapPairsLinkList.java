package com.sujeet.leetCode;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Problem24_swapPairsLinkList {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode nodeMid = head.next;
        ListNode nodeNext = nodeMid.next;
        head.next = swapPairs(nodeNext);
        nodeMid.next = head;
        return nodeMid;
    }
}
