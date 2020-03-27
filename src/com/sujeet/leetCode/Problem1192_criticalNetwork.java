package com.sujeet.leetCode;

import java.util.*;

public class Problem1192_criticalNetwork {
    int[] edges;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        edges = new int[n];
        Map<Integer, List<Integer>> graph = createGraph(connections, n);
        return getCriticalNodes(graph, n);
    }

    private Map<Integer, List<Integer>> createGraph(List<List<Integer>> connections, int n) {
        Map<Integer, List<Integer>>  graph = new HashMap<>();
        for(List<Integer> connection : connections) {
            graph.computeIfAbsent(connection.get(0), empty -> new ArrayList<Integer>()).add(connection.get(1));
            graph.computeIfAbsent(connection.get(1), empty -> new ArrayList<Integer>()).add(connection.get(0));
            edges[connection.get(1)]++;
            edges[connection.get(0)]++;
        }
        // verify all nodes have connections
        return graph;
    }

    private  List<List<Integer>> getCriticalNodes(Map<Integer, List<Integer>> graph, int n) {
        Set<List<Integer>> criticalNodes = new HashSet<>();
        for(int node = 0; node < n; node++) {
            if(edges[node] == 1) {
                int otherNode = graph.get(node).get(0);
                List<Integer> list = Arrays.asList(node, otherNode);
                Collections.sort(list);
                criticalNodes.add(list);
            }
        }
        return new ArrayList<>(criticalNodes);
    }
}
