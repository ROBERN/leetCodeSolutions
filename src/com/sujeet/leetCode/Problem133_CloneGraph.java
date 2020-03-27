package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Problem133_CloneGraph {
    Map<Integer, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        if(visited.containsKey(node.val)) {
            return visited.get(node.val);
        }
        ArrayList<Node> neighs = new ArrayList<>();
        Node res = new Node(node.val, neighs);
        visited.put(node.val, res);
        for(Node neigh : node.neighbors) {
            neighs.add(cloneGraph(neigh));
        }
        return res;
    }
}
