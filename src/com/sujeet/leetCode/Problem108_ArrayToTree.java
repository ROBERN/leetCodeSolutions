package com.sujeet.leetCode;

public class Problem108_ArrayToTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int l, int r) {
        if(r < l) {
            return null;
        }
        int rootIdx = l + (r-l)/2;
        TreeNode root = new TreeNode(nums[rootIdx]);
        if(r!=l) {
            root.left = sortedArrayToBST(nums, l, rootIdx -1);
            root.right = sortedArrayToBST(nums, rootIdx + 1, r);
        }

        return root;
    }
}
