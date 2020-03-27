package com.sujeet.leetCode;

public class Problem105_buildTreeFromPreOrderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(inorder, 0, inorder.length-1, preorder, 0, preorder.length-1);
    }

    private TreeNode buildTree(
            int[] inorder, int stIn, int endIn, int[] preorder, int stPo, int endPo
    ) {
        if(stPo > endPo) {
            return null;
        }
        int rootVal = preorder[stPo];
        TreeNode node = new TreeNode(rootVal);
        int inIndex = binSearch(inorder, stIn, endIn, rootVal);
        int len = inIndex - stIn;
        node.left = buildTree(inorder, stIn, inIndex-1, preorder, stPo+1, stPo+len);
        node.right = buildTree(inorder, inIndex+1, endIn, preorder, stPo+len+1, endPo);
        return node;
    }

    private int binSearch(int[] inorder, int stIn, int endIn, int rootVal) {
        if(endIn < stIn) {
            return -1;
        }
        int mid = stIn + (endIn-stIn)/2;
        if(inorder[mid] == rootVal) {
            return mid;
        }
        int left = binSearch(inorder, stIn, mid-1, rootVal);
        if(left != -1) {
            return left;
        }
        return binSearch(inorder, mid+1, endIn, rootVal);
    }
}
