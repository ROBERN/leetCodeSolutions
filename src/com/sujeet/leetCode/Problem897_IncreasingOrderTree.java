package com.sujeet.leetCode;

public class Problem897_IncreasingOrderTree {
    public TreeNode increasingBST(TreeNode root) {
        if(root == null) {
            return null;
        }

        root.right = increasingBST(root.right);

        TreeNode leftNode = increasingBST(root.left);
        TreeNode endLeft = leftNode;
        while(endLeft != null && endLeft.right !=null) {
            endLeft = endLeft.right;
        }
        if(endLeft != null) {
            endLeft.right = root;
        }
        root.left = null;

        return leftNode != null ? leftNode : root;
    }
}
