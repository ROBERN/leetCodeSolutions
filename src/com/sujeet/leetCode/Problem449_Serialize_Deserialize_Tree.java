package com.sujeet.leetCode;

import javafx.util.Pair;

public class Problem449_Serialize_Deserialize_Tree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(root.val));
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append(",null");
            return;
        }
        sb.append(",").append(node.val);
        serialize(node.left);
        serialize(node.right);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()) {
            return null;
        }
        String[] nodeString = data.split(",");
        if(nodeString.length == 0) {
            return null;
        }

        return createTree(nodeString, 0).getKey();
    }

    private Pair<TreeNode, Integer> createTree(String[] nodeString, int index) {
        if(nodeString.length < index || nodeString[index].equals("null")) {
            return new Pair<>(null, index);
        }

        TreeNode node = new TreeNode(Integer.parseInt(nodeString[index]));
        Pair<TreeNode, Integer> leftPair = createTree(nodeString, index+1);
        Pair<TreeNode, Integer> rightPair = createTree(nodeString, leftPair.getValue()+1);
        node.left = leftPair.getKey();
        node.right = rightPair.getKey();
        return new Pair<>(node, rightPair.getValue());
    }

    public static void main(String[] args) {

    }
}
