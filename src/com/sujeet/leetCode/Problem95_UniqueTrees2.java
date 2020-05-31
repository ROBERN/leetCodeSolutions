package com.sujeet.leetCode;

import com.sun.tools.javac.util.Pair;

import java.util.*;

public class Problem95_UniqueTrees2 {
    private int N;
    Map<Pair<Integer, Integer>, List<TreeNode>> cache = new HashMap<>();
    public List<TreeNode> generateTrees(int n) {
        N=n;
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int st, int end) {
        if(end < st) {
            return Collections.emptyList();
        }
        if(st == end){
            return Collections.singletonList(new TreeNode(st));
        }
        Pair<Integer, Integer> pr = new Pair<>(st, end);
        if(cache.containsKey(pr))
            return cache.get(pr);
        List<TreeNode> result = new ArrayList<>();
        for(int k = st; k <= end; k++) {
            List<TreeNode> leftTrees = new ArrayList<>(generateTrees(st, k-1));
            List<TreeNode> rightTrees = new ArrayList<>(generateTrees(k+1, end));

            if(leftTrees.size() == 0)
                leftTrees.add(new TreeNode(N+1));
            if(rightTrees.size() == 0)
                rightTrees.add(new TreeNode(N+1));

            for(TreeNode left : leftTrees) {
                for(TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(k);
                    root.left = left.val == N+1 ? null : left;
                    root.right = right.val == N+1 ? null : right;
                    result.add(root);
                }
            }
        }
        cache.put(pr, result);
        return result;
    }

    public static void main(String[] args) {
        Problem95_UniqueTrees2 obj = new Problem95_UniqueTrees2();
        obj.generateTrees(3);
    }
}
