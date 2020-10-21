package com.sujeet.leetCode;

public class Problem237_DeleteNodeInLL {
    public void deleteNode(ListNode node) {
        if(node == null || node.next == null) {
            return;
        }
        node.val = node.next.val;
        if(node.next.next == null) {
            node.next = null;
        } else {
            deleteNode(node.next);
        }

    }
}
