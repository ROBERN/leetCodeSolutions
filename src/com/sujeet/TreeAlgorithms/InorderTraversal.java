package com.sujeet.TreeAlgorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InorderTraversal {


    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> inOrderTraversal = new ArrayList<>();
        recur(root, inOrderTraversal);
        return inOrderTraversal;
    }

    private void recur(TreeNode node, List<Integer> inOrderTraversal) {
        if (node == null)
            return;
        recur(node.left, inOrderTraversal);
        inOrderTraversal.add(node.val);
        recur(node.right, inOrderTraversal);
    }

    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Deque<TreeNode> stk = new ArrayDeque<>();
        TreeNode current = root;
        while(!stk.isEmpty() || current != null) {
            while(current != null) {
                stk.addLast(current);
                current = current.left;
            }
            TreeNode node = stk.pollLast();
            result.add(node.val);
            current = node.right;
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
