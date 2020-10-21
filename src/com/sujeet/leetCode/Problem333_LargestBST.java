package com.sujeet.leetCode;

public class Problem333_LargestBST {
    private int maxSize = 0;
    private class SubTree {
        int min;
        int max;
        int size;
        SubTree(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        largestBST(root);
        return maxSize;
    }

    private SubTree largestBST(TreeNode node) {
        if(node == null)
            return null;
        SubTree leftSubTree = largestBST(node.left);
        int min = node.val;
        int max = node.val;
        int size = 1;
        if(leftSubTree != null) {
            if(leftSubTree.max >= node.val) {
                return null;
            }
            min = leftSubTree.min;
            size += leftSubTree.size;
        }
        SubTree rightSubTree = largestBST(node.right);
        if(rightSubTree != null) {
            if(rightSubTree.min <= node.val) {
                return null;
            }
            max = rightSubTree.max;
            size += rightSubTree.size;
        }
        maxSize = Math.max(maxSize, size);
        return new SubTree(min, max, size);
    }
}
