package com.sujeet.leetCode;

import  javafx.util.Pair;

public class Problem1290_BinaryLinkedListToNumber {
    public int getDecimalValue(ListNode head) {
        return getDecimalValue1(head).getKey();
    }

    public Pair<Integer, Integer> getDecimalValue1(ListNode node) {
        if(node == null)
            return new Pair<>(0, -1);
        Pair<Integer, Integer> ret =  getDecimalValue1(node.next);
        int calc = ret.getKey();
        int idx = ret.getValue()+1;
        if(node.val == 1) {
            calc += Math.pow(2, idx);
        }
        return new Pair<>(calc, idx);
    }
}
