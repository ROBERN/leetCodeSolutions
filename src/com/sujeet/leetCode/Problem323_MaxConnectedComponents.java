package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem323_MaxConnectedComponents {
    private boolean[] visited;
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = createGraph(n, edges);
        visited = new boolean[n];
        List<List<Integer>> scc = new ArrayList<>();
        for(int idx = 0; idx < n; idx++) {
            List<Integer> component = new ArrayList<>();
            dfs(graph, idx, component);
            if(component.size() != 0)
                scc.add(component);
        }

        return scc.size();
    }

    private void dfs(Map<Integer, List<Integer>> graph, int idx, List<Integer> component) {
        if(visited[idx])
            return;
        visited[idx] = true;
        component.add(idx);
        for(int adj : graph.getOrDefault(idx, new ArrayList<>())) {
            dfs(graph, adj, component);
        }
    }

    private Map<Integer, List<Integer>> createGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] edge : edges) {
            graph.computeIfAbsent(edge[0], empty -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], empty -> new ArrayList<>()).add(edge[0]);
        }

        return graph;
    }
}
