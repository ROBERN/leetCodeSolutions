package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

public class Problem662_GetWidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        int maxWidth = 1;
        Deque<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.addFirst(new Pair<>(root, 0));

        while(!queue.isEmpty()) {
            Deque<Pair<TreeNode, Integer>> tempQueue = new LinkedList<>();
            while(!queue.isEmpty()) {
                Pair<TreeNode, Integer> nodeVal = queue.pollLast();
                if(nodeVal.getKey().left != null)
                    tempQueue.addFirst(new Pair<>(nodeVal.getKey().left, 2*nodeVal.getValue()));
                if(nodeVal.getKey().right != null)
                    tempQueue.addFirst(new Pair<>(nodeVal.getKey().right, 2*nodeVal.getValue()+1));
            }
            maxWidth = Math.max(maxWidth, tempQueue.getLast().getValue() - tempQueue.getFirst().getValue());
            queue = tempQueue;
        }
        return maxWidth;
    }
}
