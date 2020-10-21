package com.sujeet.leetCode;

public class Problem226_InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }

        TreeNode left = invertTree(root.right);
        root.right = invertTree(root.left);
        root.left = left;
        return root;
    }
}
