package com.sujeet.leetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Problem1305_AllElementsTreeInSortedOrder {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        while(!stack1.isEmpty() || !stack2.isEmpty() || root1 != null || root2 != null) {
            while(root1 != null) {
                stack1.push(root1);
                root1 = root1.left;
            }
            while(root2 != null) {
                stack2.push(root2);
                root2 = root2.left;
            }
            if(stack2.isEmpty() || !stack1.isEmpty() &&
                    stack1.getFirst().val <= stack2.getFirst().val) {
                root1 = stack1.pop();
                result.add(root1.val);
                root1 = root1.right;
            }
            else {
                root2 = stack2.pop();
                result.add(root2.val);
                root2 = root2.right;
            }
        }
        return result;
    }
}
