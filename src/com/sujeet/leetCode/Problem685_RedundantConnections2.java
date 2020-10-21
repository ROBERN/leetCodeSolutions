package com.sujeet.leetCode;

import java.util.*;

/**
 * Incorrect use case in the question :
 *
 * Your input[[2,1],[3,1],[4,2],[1,4]]
 * Output [1,4]
 * Expected [2,1]
 */
public class Problem685_RedundantConnections2 {
    private Map<Integer, List<Integer>> graph;
    private boolean[] visited;
    private int[] parent;
    private int cycleNode = -1;
    private int[] result;
    private List<List<Integer>> candidates;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        graph = new HashMap<>();
        visited = new boolean[n +1];
        parent = new int[n +1];
        result = new int[2];
        candidates = new ArrayList<>();

        createGraph(edges);

        for(int i = 1; i <= edges.length; i++) {
            if(!visited[i])
                dfs(i, -1, new HashSet<>());
        }
        if(cycleNode != -1)
            candidates = getCyclePairs();

        for(int[] edge : edges) {
            for (List<Integer> candidate : candidates) {
                if ((edge[0] == candidate.get(0) && edge[1] == candidate.get(1)) ||
                        edge[0] == candidate.get(1) && edge[1] == candidate.get(0)) {
                    result[0] = edge[0];
                    result[1] = edge[1];
                }
            }
        }

        return result;
    }

    private List<List<Integer>> getCyclePairs() {
        List<List<Integer>> ans = new ArrayList<>();
        int parentNode = parent[cycleNode];
        int childNode = cycleNode;
        while(parentNode != cycleNode) {
            ans.add(Arrays.asList(parentNode, childNode));
            childNode = parentNode;
            parentNode = parent[childNode];
        }
        ans.add(Arrays.asList(parentNode, childNode));

        return ans;
    }


    private void dfs(int vertex, int pred, Set<Integer> visiting) {
        if(cycleNode != -1)
            return;
        if(visited[vertex]) {
            candidates.add(Arrays.asList(pred, vertex));
            candidates.add(Arrays.asList(parent[vertex], vertex));
            result[0] = pred;
            result[1] = vertex;
            return;
        }
        parent[vertex] = pred;
        if(visiting.contains(vertex)) {
            cycleNode = vertex;
            return;
        }
        visiting.add(vertex);
        for(int adj : graph.getOrDefault(vertex, new ArrayList<>())) {
            if(adj == pred)
                continue;
            dfs(adj, vertex, visiting);
        }
        visiting.remove(vertex);
        visited[vertex] = true;
    }

    private void createGraph(int[][] edges) {
        for(int[] edge : edges) {
            graph.computeIfAbsent(edge[0], empty -> new ArrayList<>()).add(edge[1]);
        }
    }

    public static void main(String[] args) {
        Problem685_RedundantConnections2 obj = new Problem685_RedundantConnections2();
        obj.findRedundantDirectedConnection(new int[][]{
                {2, 1},
                {3, 1},
                {4, 2},
                {1, 4}
        });
    }
}
/**
 [[2,1],[3,1],[4,2],[1,4]]
 **/