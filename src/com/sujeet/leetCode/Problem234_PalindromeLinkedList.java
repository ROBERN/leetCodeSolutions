package com.sujeet.leetCode;

public class Problem234_PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(prev != null) {
            prev.next = null;
        }
        ListNode rev = reverse(slow);
        while(head != null && rev != null) {
            if(head.val != rev.val) {
                return false;
            }
            head =head.next;
            rev = rev.next;
        }
        return true;
    }

    private ListNode reverse(ListNode node) {
        if(node == null || node.next ==null) {
            return node;
        }
        ListNode ret = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return ret;
    }
}
