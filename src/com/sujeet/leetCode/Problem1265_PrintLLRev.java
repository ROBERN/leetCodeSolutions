package com.sujeet.leetCode;

interface ImmutableListNode {
      public void printValue(); // print the value of this node.
      public ImmutableListNode getNext(); // return the next node.
};

public class Problem1265_PrintLLRev {
    public void printLinkedListInReverse(ImmutableListNode head) {
        if(head == null) {
            return;
        }
        printLinkedListInReverse(head.getNext());
        head.printValue();
    }
}
