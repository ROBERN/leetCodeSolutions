package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Problem429_NaryLevelOrder {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    List<List<Integer>> sol = new ArrayList<>();
    public List<List<Integer>> levelOrder(Node root) {
        if(root ==null) {
            return sol;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        levelOrderTraverse(q);
        return sol;
    }

    private void levelOrderTraverse(Queue<Node> q) {
        if(q.isEmpty()) {
            return;
        }
        Queue<Node> newQueue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        while(!q.isEmpty()) {
            Node curr = q.poll();
            list.add(curr.val);
            for(Node child : curr.children) {
                newQueue.offer(child);
            }
        }
        sol.add(list);
        levelOrderTraverse(newQueue);
    }
}
