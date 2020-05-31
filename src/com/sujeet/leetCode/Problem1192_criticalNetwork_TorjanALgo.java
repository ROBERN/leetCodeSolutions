package com.sujeet.leetCode;

import java.util.*;

public class Problem1192_criticalNetwork_TorjanALgo {
    private int time;
    private int[] visited;
    private int[] lowTime;
    private Map<Integer, List<Integer>> graph;
    private Set<List<Integer>> critical;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //init member variables
        graph = createGraph(connections);
        time = 0;
        visited = new int[n];
        lowTime = new int[n];
        Arrays.fill(visited, -1);
        Arrays.fill(lowTime, Integer.MAX_VALUE);
        critical = new HashSet<>();

        dfs(0, -1);
        return new ArrayList<>(critical);
    }

    private void dfs(int vertex, int parent) {
        if(visited[vertex] >= 0)
            return;
        int childCount = 0;
        visited[vertex] = time;
        lowTime[vertex] = time;
        time++;
        for(int adj : graph.getOrDefault(vertex, new ArrayList<>())) {
            if(adj == parent)
                continue;
            if(visited[adj] < 0) {
                childCount++;
                dfs(adj, vertex);
                if(visited[vertex] <= lowTime[adj] && parent != -1) {
                    List<Integer> list = Arrays.asList(vertex, adj);
                    Collections.sort(list);
                    critical.add(list);
                }
                else
                    lowTime[adj] = Math.min(lowTime[vertex], lowTime[adj]);
            } else {
                lowTime[adj] = Math.min(lowTime[vertex], visited[adj]);
            }
        }

        // if((parent == -1 && childCount >= 2)) {
        //     for(int adj : graph.getOrDefault(vertex, new ArrayList<>())) {
        //         if()
        //     }
        // }
    }


    private Map<Integer, List<Integer>> createGraph(List<List<Integer>> connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(List<Integer> edge : connections) {
            graph.computeIfAbsent(edge.get(0), empty -> new ArrayList<>()).add(edge.get(1));
            graph.computeIfAbsent(edge.get(1), empty -> new ArrayList<>()).add(edge.get(0));
        }
        return graph;
    }

    public static void main(String[] args) {
        Problem1192_criticalNetwork_TorjanALgo obj = new Problem1192_criticalNetwork_TorjanALgo();
        obj.criticalConnections(4, Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(1, 2),
                Arrays.asList(2, 0),
                Arrays.asList(1, 3)
        ));
    }
}
