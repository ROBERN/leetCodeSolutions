package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem314_VerticalTreeList {
    private List<List<Integer>> result;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        Pair<Integer, Integer> leftRightMost = getLeftRightMost(root, 0);
        int len = leftRightMost.getValue() - leftRightMost.getKey() +1;

        result = new ArrayList<>();
        for(int i = 0; i < len; i++)
            result.add(new ArrayList<>());

        int startIdx = -1*leftRightMost.getKey();

        setVerticalElements(root, startIdx);

        return result;
    }

    private void setVerticalElements(TreeNode node, int pos) {
        if(node == null)
            return;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(node, pos));
        while(!queue.isEmpty()) {
            Queue<Pair<TreeNode, Integer>> tempQueue = new LinkedList<>();
            while(!queue.isEmpty()) {
                Pair<TreeNode, Integer> entry = queue.poll();
                int index = entry.getValue();
                TreeNode node1 = entry.getKey();
                result.get(index).add(node1.val);
                if(node1.left != null)
                    tempQueue.add(new Pair<>(node1.left, index-1));

                if(node1.right != null)
                    tempQueue.add(new Pair<>(node1.right, index+1));
            }
            queue = tempQueue;
        }
    }

    private Pair<Integer, Integer> getLeftRightMost(TreeNode node, int pos) {
        if(node == null) {
            return new Pair<>(pos, pos);
        }
        Pair<Integer, Integer> leftDim = new Pair<>(pos, pos);
        if(node.left != null) {
            leftDim = getLeftRightMost(node.left, pos-1);
        }

        Pair<Integer, Integer> rightDim = new Pair<>(pos, pos);
        if(node.right != null) {
            rightDim = getLeftRightMost(node.right, pos+1);
        }

        return new Pair<>(Math.min(leftDim.getKey(), rightDim.getKey()), Math.max(leftDim.getValue(), rightDim.getValue()));
    }
}
