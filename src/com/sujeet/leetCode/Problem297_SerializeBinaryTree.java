package com.sujeet.leetCode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem297_SerializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> queue = new LinkedList<>();
        sb.append(root.val);
        queue.add(root);
        while(!queue.isEmpty()) {
            Deque<TreeNode> tempQueue = new LinkedList<>();
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                sb.append(",");
                if(node.left != null) {
                    tempQueue.add(node.left);
                    sb.append(node.left.val);
                } else {
                    sb.append("null");
                }

                sb.append(",");
                if(node.right != null) {
                    tempQueue.add(node.right);
                    sb.append(node.right.val);
                } else {
                    sb.append("null");
                }
            }
            queue = tempQueue;
        }
        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty() || data.equals("null"))
            return null;
        String[] items = data.split(",");

        if(items.length == 0)
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(items[0]));
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int idx = 1;

        while(idx < items.length) {
            TreeNode node = queue.poll();
            if(items[idx].equals("null")) {
                node.left = null;
            } else {
                node.left = new TreeNode(Integer.parseInt(items[idx]));
                queue.add(node.left);
            }
            idx++;
            if(items[idx].equals("null")) {
                node.right = null;
            } else {
                node.right = new TreeNode(Integer.parseInt(items[idx]));
                queue.add(node.right);
            }
            idx++;
        }

        return root;
    }
}
