package com.sujeet.leetCode;

public class Problem543_diameterOfBinaryTree {
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 1;
        depth(root);
        return diameter-1;
    }

    public int depth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        if(right+left +1 > diameter) {
            diameter = right+left +1;
        }
        return Math.max(left, right)+1;
    }
}
