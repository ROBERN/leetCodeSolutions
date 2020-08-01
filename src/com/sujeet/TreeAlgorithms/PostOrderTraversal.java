package com.sujeet.TreeAlgorithms;

import java.util.*;

public class PostOrderTraversal {

    public List<Integer> postOrderTraversalRecursive(TreeNode root) {
        List<Integer> postOrderTraversal = new ArrayList<>();
        recur(root, postOrderTraversal);
        return postOrderTraversal;
    }

    private void recur(TreeNode node, List<Integer> postOrderTraversal) {
        if (node == null)
            return;
        recur(node.left, postOrderTraversal);
        recur(node.right, postOrderTraversal);
        postOrderTraversal.add(node.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrder = new ArrayList<>();
        if(root == null)
            return postOrder;
        Deque<TreeNode> stk = new LinkedList<>();
        stk.add(root);
        TreeNode curr = root;
        Set<TreeNode> visited = new HashSet<>();
        visited.add(root);
        while(!stk.isEmpty()) {
            curr = stk.peekLast();

            TreeNode leftCurr = curr.left;
            TreeNode rightCurr = curr.right;
            if(rightCurr != null && !visited.contains(rightCurr)) {
                stk.addLast(rightCurr);
            }
            if(leftCurr != null && !visited.contains(leftCurr)) {
                stk.addLast(leftCurr);
            }
            visited.add(curr);

            if((leftCurr == null || visited.contains(leftCurr)) && (rightCurr == null || visited.contains(rightCurr))) {
                stk.pollLast();// if both child are visited or null
                postOrder.add(curr.val);
            }
        }

        return postOrder;
    }
}
