package com.sujeet.leetCode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem331_VerifyPreorderSerialization {
    private static class Node{
        String c;
        int childSeen;
        Node(String ch) {
            childSeen = 0;
            c = ch;
        }
        void seen() {
            childSeen++;
        }

        boolean allChildSeen() {
            return childSeen == 2;
        }
    }
    public static  boolean isValidSerialization(String preorder) {
        Deque<Node> stack = new LinkedList<>();
        String[] nodes = preorder.split(",");
        boolean rootFound = false;
        for(String c : nodes) {
            if(!c.equals("#")) {
                if (rootFound && stack.isEmpty())
                    return false;
                rootFound = true;
                stack.addLast(new Node(c));
            } else {
                if(stack.isEmpty()) {
                    return preorder.length() == 1;
                }
                Node lastNode = stack.peekLast();
                while(!stack.isEmpty()) {
                    lastNode.seen();
                    if (lastNode.allChildSeen()) {
                        stack.pollLast();
                    } else {
                        break;
                    }
                    lastNode = stack.peekLast();
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Problem331_VerifyPreorderSerialization.isValidSerialization("9,3,4,#,#,1,#,#,#,2,#,6,#,#");
    }
}
