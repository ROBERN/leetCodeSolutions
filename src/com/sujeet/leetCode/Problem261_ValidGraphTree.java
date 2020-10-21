package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem261_ValidGraphTree {
    private int N;
    public boolean validTree(int n, int[][] edges) {
        this.N = n;
        Map<Integer, List<Integer>> graph = createGraph(edges);
        return isValidTree(graph);
    }

    private Map<Integer, List<Integer>> createGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            graph.computeIfAbsent(edge[0], empty -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], empty -> new ArrayList<>()).add(edge[0]);
        }

        return graph;
    }

    private boolean[] visited;
    private boolean isValidTree(Map<Integer, List<Integer>> graph) {
        visited = new boolean[N];
        if(!isValidTree(graph, 0, -1))
            return false;

        return isAllVisited(visited);
    }

    private boolean isValidTree(Map<Integer, List<Integer>> graph, int node, int parent) {
        if(visited[node])
            return false;
        visited[node] = true;
        for(int adj : graph.getOrDefault(node, new ArrayList<>())) {
            if(adj == parent)
                continue;
            if(!isValidTree(graph, adj, node))
                return false;
        }
        return true;
    }

    private static boolean isAllVisited(boolean[] visited) {
        for(boolean isVisited : visited) {
            if(!isVisited)
                return false;
        }
        return true;
    }
}
