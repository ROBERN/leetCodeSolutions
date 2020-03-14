package com.sujeet;

public class Problem445_AddTwoNumbers2 {
    int carry = 0;
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head1 = l1;
        ListNode head2 = l2;
        int diff = lengthNode(l1) - lengthNode(l2);
        l1 = getNth(l1, diff);
        l2 = getNth(l2, -1 * diff);
        ListNode ret = addNode(l1, l2);
        ListNode head = null;
        if(diff > 0) {
            head = addNodeLimit(head1, l1);
        } else if(diff < 0) {
            head = addNodeLimit(head2, l2);
        }
        head = append(head, ret);
        if(carry != 0) {
            ListNode node = new ListNode(carry);
            node.next = head;
            return node;
        }
        return head;
    }

    private ListNode addNode(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) {
            return null;
        }

        ListNode ret = addNode(l1.next, l2.next);
        int v1 = l1.val;
        int v2 = l2.val;

        int nodeVal = (v1+v2+carry)%10;
        carry = (v1+v2+carry)/10;
        ListNode node = new ListNode(nodeVal);
        node.next = ret;
        return node;
    }

    private ListNode addNodeLimit(ListNode l1, ListNode limit) {
        if(l1 == limit) {
            return null;
        }

        ListNode ret = addNodeLimit(l1.next, limit);

        int nodeVal = (l1.val+carry)%10;
        carry = (l1.val+carry)/10;
        ListNode node = new ListNode(nodeVal);
        node.next = ret;
        return node;
    }

    private ListNode getNth(ListNode node, int step) {
        while(node !=null && step>0) {
            node= node.next;
            step--;
        }
        return node;
    }

    private int lengthNode(ListNode node) {
        int len = 0;
        while(node != null){
            node =node.next;
            len++;
        }
        return len;
    }

    private ListNode append(ListNode node1, ListNode node2) {
        ListNode head1 = node1;
        if(node1 == null) {
            return node2;
        }
        while(node1.next != null) {
            node1 = node1.next;
        }
        node1.next =node2;
        return head1;
    }
}
