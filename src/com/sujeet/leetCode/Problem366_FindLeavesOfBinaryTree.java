package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem366_FindLeavesOfBinaryTree {
    List<List<Integer>>  result = new ArrayList<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        int depth = getDepth(root);
        for(int i = 0; i < depth; i++) {
            result.add(new ArrayList<>());
        }
        populate(root);
        return result;
    }

    private int populate(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int leftDepth = populate(node.left);
        int rightDepth = populate(node.right);
        int idx = 1+Math.max(leftDepth, rightDepth);

        result.get(idx-1).add(node.val);
        return idx;
    }

    private int getDepth(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int leftDepth = getDepth(node.left);
        int rightDepth = getDepth(node.right);
        return 1+Math.max(leftDepth, rightDepth);
    }
}
