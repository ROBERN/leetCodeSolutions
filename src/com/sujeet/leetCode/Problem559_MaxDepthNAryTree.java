package com.sujeet.leetCode;

public class Problem559_MaxDepthNAryTree {
    public int maxDepth(NaryNode root) {
        if(root == null)
            return 0;
        int maxDepth = 0;
        for (NaryNode node : root.children) {
            maxDepth = Math.max(maxDepth, maxDepth(node));
        }

        return maxDepth+1;
    }
}
