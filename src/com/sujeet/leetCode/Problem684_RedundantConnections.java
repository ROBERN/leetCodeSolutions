package com.sujeet.leetCode;

import java.util.*;

public class Problem684_RedundantConnections {
    private Map<Integer, List<Integer>> graph;
    private boolean[] visited;
    private int[] parent;
    private int N;
    private int cycleNode = -1;
    public int[] findRedundantConnection(int[][] edges) {
        N = edges.length;
        graph = new HashMap<>();
        visited = new boolean[N+1];
        parent = new int[N+1];
        createGraph(edges);

        for(int i = 1; i <= N; i++)
            dfs(i, -1);
        List<List<Integer>> candidates = getCyclePairs();
        int[] result = new int[2];
        for(int[] edge : edges) {
            for(int idx = 0; idx < candidates.size(); idx++)
                if((edge[0] == candidates.get(idx).get(0) && edge[1] == candidates.get(idx).get(1)) ||
                        edge[0] == candidates.get(idx).get(1) && edge[1] == candidates.get(idx).get(0)) {
                    result[0] = edge[0];
                    result[1] = edge[1];
                }
        }

        return result;
    }

    private List<List<Integer>> getCyclePairs() {
        List<List<Integer>> candidates = new ArrayList<>();
        int parentNode = parent[cycleNode];
        int childNode = cycleNode;
        while(parentNode != cycleNode) {
            candidates.add(Arrays.asList(parentNode, childNode));
            childNode = parentNode;
            parentNode = parent[childNode];
        }

        return candidates;
    }


    private void dfs(int vertex, int pred) {
        if(cycleNode != -1)
            return;
        parent[vertex] = pred;
        if(visited[vertex]) {
            cycleNode = vertex;
            return;
        }
        visited[vertex] = true;
        for(int adj : graph.getOrDefault(vertex, new ArrayList<>())) {
            if(adj == pred)
                continue;
            dfs(adj, vertex);
        }
    }

    private void createGraph(int[][] edges) {

        for(int[] edge : edges) {
            graph.computeIfAbsent(edge[0], empty -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], empty -> new ArrayList<>()).add(edge[0]);
        }
    }

    public static void main(String[] args) {
        Problem684_RedundantConnections obj = new Problem684_RedundantConnections();
        int[] ans = obj.findRedundantConnection(new int[][]{
                {1, 2},
                {1, 3},
                {2, 3}
        });
        for (int i = 0 ; i < ans.length; i++)
            System.out.println(ans[i]);
    }
}
