package com.sujeet.TreeAlgorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PreOrderTraversal {

    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> preOrderTraversal = new ArrayList<>();
        recur(root, preOrderTraversal);
        return preOrderTraversal;
    }

    private void recur(TreeNode node, List<Integer> preOrderTraversal) {
        if (node == null)
            return;
        recur(node.left, preOrderTraversal);
        preOrderTraversal.add(node.val);
        recur(node.right, preOrderTraversal);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        if(root == null) {
            return result;
        }
        Deque<TreeNode> stk = new ArrayDeque<>();
        stk.addLast(root);
        while(!stk.isEmpty()) {
            TreeNode node = stk.pollLast();
            if(node.right != null) {
                stk.addLast(node.right);
            }
            if(node.left != null) {
                stk.addLast(node.left);
            }
            result.add(node.val);
        }
        return result;
    }
}
