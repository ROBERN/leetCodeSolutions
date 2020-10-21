package com.sujeet.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem1059_AllPathsEndAtDestination {

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {

        Map<Integer, Set<Integer>> graph = constructGraph(edges);
        if(graph.containsKey(destination) && graph.get(destination).size() > 0)
            return false; // outDegree from destination

        return endsAtDestination(graph, source, destination, new HashSet<>());
    }

    private boolean endsAtDestination(
            Map<Integer, Set<Integer>> graph,
            int source,
            int destination,
            Set<Integer> visiting
    ) {
        if(source == destination) {
            return true;
        }
        if(visiting.contains(source) || graph.getOrDefault(source, new HashSet<>()).size() == 0) {
            return false;
        }

        visiting.add(source);

        for(int neigh : graph.get(source)) {
            if(!endsAtDestination(graph, neigh, destination, visiting)) {
                return false;
            }
        }
        visiting.remove(source);

        return true;
    }

    private Map<Integer, Set<Integer>> constructGraph(int[][] edges) {
        Map<Integer, Set<Integer>> graph  = new HashMap<>();

        for(int[] edge : edges) {
            graph.computeIfAbsent(edge[0], empty -> new HashSet<>()).add(edge[1]);
        }

        return graph;
    }
}
